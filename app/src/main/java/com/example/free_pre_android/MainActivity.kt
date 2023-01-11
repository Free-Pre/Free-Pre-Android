package com.example.free_pre_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.free_pre_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}