package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.*
import com.example.free_pre_android.databinding.ActivityFreeSettingBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreeSettingActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityFreeSettingBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var email=""
    var nickname:String?=""
    var first_period:Boolean?=false
    var cycle:Int?=0;
    var term:Int?=0;
    var notice:Boolean?=false
    var pregnancy:Boolean?=false
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityFreeSettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email = sharedPreferences.getString("emailKey","there's no email").toString()
        getUserInfo()
        //초기에 설정한 닉네임 값 가져오기
        //val nickname = intent.getStringExtra("nickname")
        //Log.d("FreeSettingNickname",nickname.toString())
        viewBinding.textNickname.text="Hello! $nickname"
        viewBinding.switchNotification.isChecked= (notice == true)
        viewBinding.switchPregnancy.isChecked=(pregnancy==true)
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        //닉네임 수정
        viewBinding.btnEditNickname.setOnClickListener {
            //닉네임 엑티비티로 넘어가서 닉네임 띄우고 다시 저장.
        }
        //주기 입력
        viewBinding.btnRecordPeriod.setOnClickListener {
            val intent =Intent(this, EditPeriodListActivity::class.java)
            startActivity(intent)
        }
        //알람
        viewBinding.btnAlarm.setOnClickListener {
            val intent =Intent(this, FreeAlarmActivity::class.java)
            startActivity(intent)
        }

        //free->pre
        viewBinding.btnFreeToPre.setOnClickListener {
            freeToPre()
        }

        //회원탈퇴
        viewBinding.btnDeleteAccount.setOnClickListener {
            signOut()
        }
    }
    fun getUserInfo(){
        RetrofitBuilder.settingApi.settingUser(email).enqueue(object:Callback<SettingInfoDTO>{
            override fun onResponse(call: Call<SettingInfoDTO>, response: Response<SettingInfoDTO>) {
                Log.d("SETTING_GET_USER",response.body().toString())
                if(response.isSuccessful){
                    if(response.body()?.isSuccess==true){
                        nickname= response.body()?.result?.nickname
                        notice=response.body()?.result?.notice
                        pregnancy=response.body()?.result?.pregnancy
                        viewBinding.textNickname.text="Hello, $nickname"
                        viewBinding.switchNotification.isChecked= (notice == true)
                        viewBinding.switchPregnancy.isChecked=(pregnancy==true)
                    }
                }
                else{
                    Log.e("SETTING_GET_USER","response fail")
                }
            }
            override fun onFailure(call: Call<SettingInfoDTO>, t: Throwable) {
                Log.e("SETTING_GET_USER",t.message.toString())

            }
        })
    }
    fun freeToPre(){
        val datainfo= VersionChangeDTO(true)
        RetrofitBuilder.versionApi.versionChange(email,datainfo).enqueue(object:
            Callback<VersionChangeResultDTO> {
            override fun onResponse(call: Call<VersionChangeResultDTO>, response: Response<VersionChangeResultDTO>) {
                Log.d("FREE_TO_PRE",response.code().toString())
                Log.d("FREE_TO_PRE",response.body().toString())
                Log.d("FREE_TO_PRE",response.message().toString())
                val intent = Intent(this@FreeSettingActivity, PreActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<VersionChangeResultDTO>, t: Throwable) {
                Log.e("FREE_TO_PRE",t.message.toString())
            }

        })
    }
    fun signOut(){
        RetrofitBuilder.userApi.deleteUser(email).enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val dialogBuilder = AlertDialog.Builder(this@FreeSettingActivity)
                dialogBuilder.setTitle("Are you sure you want to delete your account??")  //탈퇴하시겠습니까?
                    .setPositiveButton("Yse") { dialog, id ->
                        Log.d("DeleteUser","회원 탈퇴 성공")
                        val mAuth = FirebaseAuth.getInstance()
                        val currentUser = mAuth.currentUser
                        currentUser?.delete()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // 사용자 삭제 성공
                                    Log.d("DeleteUser","free: firebase 회원 탈퇴 성공")
                                    val intent = Intent(this@FreeSettingActivity, GoogleLoginActivity::class.java)
                                    startActivity(intent)
                                    this@FreeSettingActivity.finish()

                                } else {
                                    // 사용자 삭제 실패
                                    Log.d("DeleteUser","free: firebase 회원 탈퇴 실패")
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

