package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityNicknameBinding

class NicknameActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNicknameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        //이메일 데이터 가져오기 - test용
        var sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        var getEmail: String? = sharedPreferences.getString("emailKey","there's no email")
        Log.d(ContentValues.TAG,"NickNameGetEmail: $getEmail")

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, FirstPeriodActivity::class.java)
            startActivity(intent)


        }
    }
}