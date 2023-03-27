package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.DeleteUserDTO
import com.example.free_pre_android.databinding.ActivityPreSettingBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreSettingActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityPreSettingBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreSettingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        //초기에 설정한 닉네임 값 가져오기
        val nickname = intent.getStringExtra("nickname")
        Log.d("FreeSettingNickname",nickname.toString())

        //닉네임 편집
        viewBinding.btnEditNickname.setOnClickListener {

        }

        //계정탈퇴
        viewBinding.btnDeleteAccount.setOnClickListener {
            val datainfo= DeleteUserDTO(nickname.toString())
            RetrofitBuilder.userApi.deleteUser(email).enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val dialogBuilder = AlertDialog.Builder(this@PreSettingActivity)
                    dialogBuilder.setTitle("Are you sure you want to delete your account??")  //탈퇴하시겠습니까?
                        .setPositiveButton("Yse") { dialog, id ->
                            Log.d("DeleteUser","회원 탈퇴 성공")
                            val mAuth = FirebaseAuth.getInstance()
                            val currentUser = mAuth.currentUser
                            currentUser?.delete()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // 사용자 삭제 성공
                                        Log.d("DeleteUser","pre: firebase 회원 탈퇴 성공")
                                        val intent = Intent(this@PreSettingActivity, GoogleLoginActivity::class.java)
                                        startActivity(intent)
                                        this@PreSettingActivity.finish()

                                    } else {
                                        // 사용자 삭제 실패
                                        Log.d("DeleteUser","pre: firebase 회원 탈퇴 실패")
                                    }
                                }
                            //val intent = Intent(this@FreeSettingActivity, GoogleLoginActivity::class.java)
                            //Toast.makeText(this@FreeSettingActivity, "탈퇴가 완료되었습니다.",Toast.LENGTH_SHORT).show()

                        }
                        .setNegativeButton("No") { dialog, id -> }
                    dialogBuilder.show()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("DeleteUser",t.message.toString())
                }

            })

        }
    }
}