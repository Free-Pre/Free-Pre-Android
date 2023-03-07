package com.example.free_pre_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivitySymptomBinding

class SymptomActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySymptomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySymptomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}