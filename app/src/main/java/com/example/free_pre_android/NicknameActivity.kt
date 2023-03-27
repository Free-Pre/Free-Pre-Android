package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityNicknameBinding

class NicknameActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNicknameBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        //이메일 데이터 가져오기 - test용
        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        val getEmail: String? = sharedPreferences.getString("emailKey","there's no email")
        Log.d(ContentValues.TAG,"NickNameGetEmail: $getEmail")




        setNickname()
    }

    fun setNickname(){
        var nickname:String=" "
        viewBinding.btnNext.setOnClickListener {
            nickname=viewBinding.editNickname.text.toString().trim()
            if(nickname.isEmpty()){
                Toast.makeText(this,"Please enter your nickname",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, FirstPeriodActivity::class.java)
                intent.putExtra("nickname",nickname)                      //intent.getStringExtra("nickname")으로 닉네임값 가져오기
                Log.d("NicknameLog","${nickname}")                     //잘됨.
                startActivity(intent)
            }
        }
    }
}