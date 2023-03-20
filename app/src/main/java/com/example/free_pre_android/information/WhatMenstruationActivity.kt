package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityWhatMenstruationBinding
import java.io.InputStream

class WhatMenstruationActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityWhatMenstruationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWhatMenstruationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatMenstruation()
        viewBindingRun()
    }


    //생리란 무엇인가
    fun WhatMenstruation() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_what_menstruation.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatMenstruation.text = inputString
    }

    fun viewBindingRun() {
        viewBinding.run {
            //생리란 무엇인가
            layoutWhatMenstruation.setOnClickListener {
                if (layoutDetailWhatMenstruation.visibility == View.VISIBLE) {
                    layoutDetailWhatMenstruation.visibility = View.GONE
                    layoutBtnWhatMenstruation.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatMenstruation.visibility = View.VISIBLE
                    layoutBtnWhatMenstruation.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }
        }
    }
}