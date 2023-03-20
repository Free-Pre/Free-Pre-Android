package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityVaginitisBinding
import java.io.InputStream

class VaginitisActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityVaginitisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityVaginitisBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatVaginitis()
        TypeVaginitis()
        Symptoms()
        RiskFactors()
        Prevention()
        viewBindingRun()

    }

    //질염 무엇
    fun WhatVaginitis() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("vaginitis_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatVaginitis.text = inputString
    }

    //질염 유형
    fun TypeVaginitis() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("vaginitis_type.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailVaginitisType.text = inputString
    }

    //증상
    fun Symptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("vaginitis_symptoms.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailVaginitisSymptoms.text = inputString
    }

    //위험 요소
    fun RiskFactors() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("vaginitis_risk_factors.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailRiskFactors.text = inputString
    }

    //방지
    fun Prevention() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("vaginitis_prevention.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPrevention.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //질염 무엇
            layoutWhatVaginitis.setOnClickListener {
                if (layoutDetailWhatVaginitis.visibility == View.VISIBLE) {
                    layoutDetailWhatVaginitis.visibility = View.GONE
                    layoutBtnWhatVaginitis.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatVaginitis.visibility = View.VISIBLE
                    layoutBtnWhatVaginitis.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //질염 유형
            layoutVaginitisType.setOnClickListener {
                if (layoutDetailVaginitisType.visibility == View.VISIBLE) {
                    layoutDetailVaginitisType.visibility = View.GONE
                    layoutBtnVaginitisType.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailVaginitisType.visibility = View.VISIBLE
                    layoutBtnVaginitisType.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //증상
            layoutVaginitisSymptoms.setOnClickListener {
                if (layoutDetailVaginitisSymptoms.visibility == View.VISIBLE) {
                    layoutDetailVaginitisSymptoms.visibility = View.GONE
                    layoutBtnVaginitisSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailVaginitisSymptoms.visibility = View.VISIBLE
                    layoutBtnVaginitisSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //위험 요소
            layoutRiskFactors.setOnClickListener {
                if (layoutDetailRiskFactors.visibility == View.VISIBLE) {
                    layoutDetailRiskFactors.visibility = View.GONE
                    layoutBtnRiskFactors.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailRiskFactors.visibility = View.VISIBLE
                    layoutBtnRiskFactors.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //방지
            layoutPrevention.setOnClickListener {
                if (layoutDetailPrevention.visibility == View.VISIBLE) {
                    layoutDetailPrevention.visibility = View.GONE
                    layoutBtnPrevention.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPrevention.visibility = View.VISIBLE
                    layoutBtnPrevention.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}