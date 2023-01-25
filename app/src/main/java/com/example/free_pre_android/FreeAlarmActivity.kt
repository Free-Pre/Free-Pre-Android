package com.example.free_pre_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.free_pre_android.databinding.ActivityFreeAlarmBinding


class FreeAlarmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

    }
}