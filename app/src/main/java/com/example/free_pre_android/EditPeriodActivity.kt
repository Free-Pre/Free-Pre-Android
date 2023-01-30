package com.example.free_pre_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityEditPeriodBinding

class EditPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEditPeriodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}