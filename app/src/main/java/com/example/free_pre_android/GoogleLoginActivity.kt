package com.example.free_pre_android

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityGoogleLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class GoogleLoginActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityGoogleLoginBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth


    private var email: String = ""
    private var tokenId: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGoogleLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //구글 로그인
        firebaseAuth = FirebaseAuth.getInstance()
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
                Log.e(TAG, "resultCode : ${result.resultCode}")
                Log.e(TAG, "result : $result")
                if (result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        task.getResult(ApiException::class.java)?.let { account ->
                            tokenId = account.idToken
                            if (tokenId != null && tokenId != "") {
                                val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                                firebaseAuth.signInWithCredential(credential)
                                    .addOnCompleteListener {
                                        if (firebaseAuth.currentUser != null) {
                                            val user: FirebaseUser = firebaseAuth.currentUser!!
                                            email = user.email.toString()
                                            Log.e(TAG, "email : $email")
                                            val googleSignInToken = account.idToken ?: ""
                                            if (googleSignInToken != "") {
                                                Log.e(TAG, "googleSignInToken : $googleSignInToken")
                                            } else {
                                                Log.e(TAG, "googleSignInToken이 null")
                                            }
                                        }
                                    }
                            }
                        } ?: throw Exception()
                    }   catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })

        viewBinding.run {
            btnGoogleLogin.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    //프로필 정보를 요청
                    //첫째, 사용자가 로그인할 때 ID 토큰을 가져옵니다.
                    //1.Google 로그인을 구성할 때 requestIdToken 메서드를 호출하고, 서버의 웹 클라이언트ID를 전달합니다.
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))    //클라이언트 ID
                        .requestEmail()
                        .build()
                    //gso를 통해 가져올 클라이언트의 정보를 담을 googleSignInClient
                    val googleSignInClient = GoogleSignIn.getClient(this@GoogleLoginActivity, gso)

                    //로그인 부분
                    val signInIntent: Intent = googleSignInClient.signInIntent
                    launcher.launch(signInIntent)


                }
                /*
                //되긴 되는데.. login 부분을 한번 더 눌러야 닉네임 부분으로 넘어간다...
                if(success){
                    Toast.makeText(this@GoogleLoginActivity,"Login Succeed!",Toast.LENGTH_SHORT)
                    val intent = Intent(this@GoogleLoginActivity,NicknameActivity::class.java)
                    startActivity(intent)
                }*/

            }

            //로그아웃 부분
            btnGoogleLogout.setOnClickListener {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(this@GoogleLoginActivity, gso)

                googleSignInClient.signOut()
                    .addOnCompleteListener(this@GoogleLoginActivity){
                        Toast.makeText(this@GoogleLoginActivity,"Logout Success!!",Toast.LENGTH_SHORT).show()
                    }

            }
        }

    }


    //이미 로그인한 사용자인지 검사하기
    //앱 실행하자마자 띄어져야 하니까 onStart()에다가
    override fun onStart() {
        super.onStart()
        val account = this?.let{GoogleSignIn.getLastSignedInAccount(this)}  //로그인한 기존 사용자인지 확인 //it...?
        if (account != null){
            Toast.makeText(this@GoogleLoginActivity,"You are already logged in!",Toast.LENGTH_SHORT).show()

            //이미 로그인 되어 있을 경우 바로 NickNameActivity로 넘어가도록!
           //startActivity(Intent(this,NicknameActivity::class.java))

        }else{
            Toast.makeText(this@GoogleLoginActivity,"You are not logged in yet!",Toast.LENGTH_SHORT).show()
        }

    }

    //1. 이미 로그인 -> 자동로그인이 되어있는 상태
    //2. 자동 로그인이 안되어 있는 상태
    //3. 처음 로그인을 하는 상태

    /*
    override fun onBackPressed(){
        startActivity(Intent(this,GoogleLoginActivity::class.java))
    }*/

    /*이미 로그인이 되어있을 경우.. 바로 Free나 Pre 홈으로 넘어가게 하는 것도 필요하다.*/
}