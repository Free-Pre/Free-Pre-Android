package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivitySanitaryPadBinding
import java.io.InputStream

class SanitaryPadActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySanitaryPadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySanitaryPadBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatPad()
        HowToUsePad()
        ViewBindingRun()
    }

    //생리대 무엇
    fun WhatPad() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("sanitary_pad_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPad.text = inputString
    }

    //생리대 사용법
    fun HowToUsePad() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("sanitary_pad_how.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowUsePad.text = inputString
    }

    //viewBinding 동작
    fun ViewBindingRun() {
        viewBinding.run {
            //생리대 무엇?
            layoutWhatPad.setOnClickListener {
                if (layoutDetailWhatPad.visibility == View.VISIBLE) {
                    layoutDetailWhatPad.visibility = View.GONE
                    layoutBtnWhatPad.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatPad.visibility = View.VISIBLE
                    layoutBtnWhatPad.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //생리대 사용법
            layoutHowUsePad.setOnClickListener {
                if (layoutDetailHowUsePad.visibility == View.VISIBLE) {
                    layoutDetailHowUsePad.visibility = View.GONE
                    layoutBtnHowUsePad.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowUsePad.visibility = View.VISIBLE
                    layoutBtnHowUsePad.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}