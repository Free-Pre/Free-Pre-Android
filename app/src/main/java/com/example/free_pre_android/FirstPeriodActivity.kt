package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFirstPeriodBinding

class FirstPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFirstPeriodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityFirstPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnYes.setOnClickListener {
            val intent = Intent(this,RecentPeriodActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnNo.setOnClickListener {
            startActivity(Intent(this,PreActivity::class.java))
        }
    }
}
