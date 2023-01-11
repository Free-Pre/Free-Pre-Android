package com.example.free_pre_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.free_pre_android.databinding.ActivityNicknameBinding

class NicknameActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNicknameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, FirstPeriodActivity::class.java)
            startActivity(intent)
        }
    }
}