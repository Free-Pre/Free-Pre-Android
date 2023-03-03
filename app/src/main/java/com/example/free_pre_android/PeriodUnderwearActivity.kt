package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPeriodUnderwearBinding
import java.io.InputStream

class PeriodUnderwearActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityPeriodUnderwearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPeriodUnderwearBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatUnderwear()
        HowToUseUnderwear()
        viewBindingRun()
    }

    //월경속옷 무엇
    fun WhatUnderwear() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("period_underwear_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPeriodUnderwear.text = inputString
    }

    //월경속옷 사용법
    fun HowToUseUnderwear() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("period_underwear_how.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowUseUnderwear.text = inputString
    }
    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //월경속옷 무엇?
            layoutWhatPeriodUnderwear.setOnClickListener {
                if (layoutDetailWhatPeriodUnderwear.visibility == View.VISIBLE) {
                    layoutDetailWhatPeriodUnderwear.visibility = View.GONE
                    layoutBtnWhatPeriodUnderwear.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatPeriodUnderwear.visibility = View.VISIBLE
                    layoutBtnWhatPeriodUnderwear.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //월경속옷 사용법
            layoutHowUseUnderwear.setOnClickListener {
                if (layoutDetailHowUseUnderwear.visibility == View.VISIBLE) {
                    layoutDetailHowUseUnderwear.visibility = View.GONE
                    layoutBtnHowUseUnderwear.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowUseUnderwear.visibility = View.VISIBLE
                    layoutBtnHowUseUnderwear.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

        }
    }
}