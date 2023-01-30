package com.example.free_pre_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityEditPeriodListBinding

class EditPeriodListActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityEditPeriodListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditPeriodListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}