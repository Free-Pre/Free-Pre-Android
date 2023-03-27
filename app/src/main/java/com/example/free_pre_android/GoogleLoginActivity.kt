package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.free_pre_android.data.EmailCheckResultDTO
import com.example.free_pre_android.databinding.ActivityGoogleLoginBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GoogleLoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityGoogleLoginBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>    //액티비티에서 액티비티로 값 전달
    private lateinit var firebaseAuth: FirebaseAuth


    private var email: String = ""
    private var tokenId: String? = null

    lateinit var sharedPreferences: SharedPreferences
    lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGoogleLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //상태바 색상 변경 - 나머지는 분홍색, 로그인 부분만 남색
        window.statusBarColor = ContextCompat.getColor(this,R.color.primary_dark)

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
                            if (tokenId != null && tokenId != "") {   //회원가입 되어있지 않은 경우?
                                val credential: AuthCredential =
                                    GoogleAuthProvider.getCredential(account.idToken, null)
                                firebaseAuth.signInWithCredential(credential)
                                    .addOnCompleteListener {
                                        if (firebaseAuth.currentUser != null) {                         //로그인이 안되어있을 경우?
                                            val user: FirebaseUser = firebaseAuth.currentUser!!
                                            email = user.email.toString()
                                            setSharedData(
                                                "Email",
                                                "emailKey",
                                                email
                                            )    //sharedPreference 값 저장
                                            getSharedData(
                                                "Email",
                                                "emailKey"
                                            )          //sharedPreference 값 가져오기
                                            //Log.d(TAG,"email: $email")
                                            Log.e(TAG, "email : $email")
                                            val googleSignInToken = account.idToken ?: ""
                                            if (googleSignInToken != "") {
                                                Log.e(TAG, "googleSignInToken : $googleSignInToken")
                                            } else {
                                                Log.e(TAG, "googleSignInToken이 null")
                                            }
                                            //DB에 이미 있는 회원인지 확인 필요
                                            emailCheck()//DB에 email있으면 true
                                        }
                                    }
                            }
                        } ?: throw Exception()
                    } catch (e: Exception) {
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


            }

            //로그아웃 부분
            //만약 로그인이 되어있지 않았는데 로그아웃을 누르면 -> "로그인이 되어있지 않습니다"라는 토스 메시지 띄우기
            btnGoogleLogout.setOnClickListener {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(this@GoogleLoginActivity, gso)
                if(tokenId != null && tokenId != ""){
                    googleSignInClient.signOut()
                        .addOnCompleteListener(this@GoogleLoginActivity) {
                            Toast.makeText(
                                this@GoogleLoginActivity,
                                "Logout Success!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }else{
                    Toast.makeText(this@GoogleLoginActivity,"you are not login yet",Toast.LENGTH_SHORT).show()   //이게 호출됨.
                }

            }
        }

    }

    fun emailCheck(){
        RetrofitBuilder.loginApi.emailCheck(email).enqueue(object : Callback<EmailCheckResultDTO> {
            override fun onResponse(call: Call<EmailCheckResultDTO>, response: Response<EmailCheckResultDTO>) {
                Log.d("LOGIN",response.body().toString())
                if (response.isSuccessful) {//연결 성공한 경우에만 처리
                    if (response.body()?.result == true) {//회원이 DB에 존재하는 경우
                        Log.d("LOGIN","exist email")
                        startActivity(Intent(this@GoogleLoginActivity, FreeActivity::class.java))
                    } else {//회원이 DB에 존재하지 않는 경우
                        Log.d("LOGIN","new email")
                        startActivity(Intent(this@GoogleLoginActivity, NicknameActivity::class.java))
                    }
                }
                else{
                    Log.d("LOGIN","response fail")
                }
            }

            override fun onFailure(call: Call<EmailCheckResultDTO>, t: Throwable) {
                Log.e("LOGIN", t.message.toString())
            }
        })
    }


    //이미 로그인한 사용자인지 검사하기
    //앱 실행하자마자 띄어져야 하니까 onStart()에다가
    //앱 시작할 때 로그인이 되어있는 상태라면
    override fun onStart() {
        super.onStart()
        //파이어베이스에 계정이 있다면-계정이 가입되어있는 상태
        if(firebaseAuth.currentUser != null){
            val account = this?.let { GoogleSignIn.getLastSignedInAccount(this) }  //로그인한 기존 사용자인지 확인 //it...?
            if (account != null) {
                getSharedData("Email","emailKey")
                Toast.makeText(
                    this@GoogleLoginActivity,
                    "You are already logged in!",
                    Toast.LENGTH_SHORT
                ).show()
                //startActivity(Intent(this,NicknameActivity::class.java))   //로그인 되어있을 경우 바로 FreeActivity로 이동


            } else {
                Toast.makeText(
                    this@GoogleLoginActivity,
                    "You are not logged in yet!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else{//파이어베이스에 계정이 없다면-탈퇴한 경우 염두
            //계정이 가입되어있지 않은 상태입니다.
            Toast.makeText(this,"Your account is not registered.",Toast.LENGTH_SHORT).show()
        }


    }

    override fun onStop() {
        setSharedData("Email","emailKey",email)
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        setSharedData("Email","emailKey",email)
    }

    //로그인 유지 되야함
    override fun onRestart() {
        super.onRestart()
    }


    //sharedPreference
    public fun setSharedData(name: String, key: String, data: String) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.apply()

        Log.d(TAG,"SetEmail: $data")   //값 잘 들어감

        /*
        //email라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        email = sharedPreferences.getString("email", "").toString()
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show()*/
    }

    public fun getSharedData(name: String, key: String) {
        var sharedPreferences: SharedPreferences = getSharedPreferences(name, Activity.MODE_PRIVATE)
        var getEmail: String? = sharedPreferences.getString(key,"there's no email")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(TAG,"GetEmail: $getEmail")
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
