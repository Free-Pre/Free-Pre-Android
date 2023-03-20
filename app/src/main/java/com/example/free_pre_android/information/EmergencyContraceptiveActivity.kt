package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityEmergencyContraceptiveBinding
import java.io.InputStream

class EmergencyContraceptiveActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEmergencyContraceptiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEmergencyContraceptiveBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatEmergencyContraception()
        HowGetEC()
        HowQuicklyEC()
        DisadvantageEC()
        viewBindingRun()
    }


    //응급피임약 무엇
    fun WhatEmergencyContraception() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("emergency_contraceptive_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPill.text = inputString
    }

    //호르몬 어떻게 얻어
    fun HowGetEC() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("emergency_contraceptive_how_get.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowGet.text = inputString
    }

    //얼마나 빨리 복용
    fun HowQuicklyEC() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("emergency_contraceptive_how_quickly.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowQuickly.text = inputString
    }

    //부작용
    fun DisadvantageEC() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream =
            assetManager.open("emergency_contraceptive_disadvantages.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailDisadvantagePill.text = inputString
    }


    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //피임약 무엇
            layoutWhatPill.setOnClickListener {
                if (layoutDetailWhatPill.visibility == View.VISIBLE) {
                    layoutDetailWhatPill.visibility = View.GONE
                    layoutBtnWhatPill.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatPill.visibility = View.VISIBLE
                    layoutBtnWhatPill.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //어떻게 얻어?
            layoutHowGet.setOnClickListener {
                if (layoutDetailHowGet.visibility == View.VISIBLE) {
                    layoutDetailHowGet.visibility = View.GONE
                    layoutBtnHowGet.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowGet.visibility = View.VISIBLE
                    layoutBtnHowGet.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //얼마나 빨리
            layoutHowQuickly.setOnClickListener {
                if (layoutDetailHowQuickly.visibility == View.VISIBLE) {
                    layoutDetailHowQuickly.visibility = View.GONE
                    layoutBtnHowQuickly.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowQuickly.visibility = View.VISIBLE
                    layoutBtnHowQuickly.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //단점
            layoutDisadvantagePill.setOnClickListener {
                if (layoutDetailDisadvantagePill.visibility == View.VISIBLE) {
                    layoutDetailDisadvantagePill.visibility = View.GONE
                    layoutBtnDisadvantagePill.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailDisadvantagePill.visibility = View.VISIBLE
                    layoutBtnDisadvantagePill.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}