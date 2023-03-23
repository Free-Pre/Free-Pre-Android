package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityMenstrualCupBinding
import java.io.InputStream

class MenstrualCupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMenstrualCupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenstrualCupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatCup()
        HowToUseCup()
        PrecautionCup()
        viewBindingRun()
    }

    //월경컵 무엇
    fun WhatCup() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("menstrual_cup_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatCup.text = inputString
    }

    //월경컵 사용법
    fun HowToUseCup() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("menstrual_cup_how.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowUseCup.text = inputString
    }

    //월경컵 주의사항
    fun PrecautionCup() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("menstrual_cup_precaution.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPrecaution.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //월경컵 무엇
            layoutWhatCup.setOnClickListener {
                if (layoutDetailWhatCup.visibility == View.VISIBLE) {
                    layoutDetailWhatCup.visibility = View.GONE
                    layoutBtnWhatCup.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatCup.visibility = View.VISIBLE
                    layoutBtnWhatCup.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //월경컵 사용법
            layoutHowUseCup.setOnClickListener {
                if (layoutDetailHowUseCup.visibility == View.VISIBLE) {
                    layoutDetailHowUseCup.visibility = View.GONE
                    layoutBtnHowUseCup.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowUseCup.visibility = View.VISIBLE
                    layoutBtnHowUseCup.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //월경컵 주의사항
           layoutPrecaution.setOnClickListener {
                if (layoutDetailPrecaution.visibility == View.VISIBLE) {
                    layoutDetailPrecaution.visibility = View.GONE
                    layoutBtnPrecaution.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPrecaution.visibility = View.VISIBLE
                    layoutBtnPrecaution.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

        }
    }
}