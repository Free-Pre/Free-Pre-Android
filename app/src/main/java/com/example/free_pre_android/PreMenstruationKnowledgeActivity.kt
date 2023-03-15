package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPreMenstruationKnowledgeBinding
import java.io.InputStream

class PreMenstruationKnowledgeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPreMenstruationKnowledgeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreMenstruationKnowledgeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatMensturationCycle()
        WhatNormalCycle()
        HowLongPeriod()
        WhenStartPeriod()
        WhatSymptoms()
        viewBindingRun()
    }

    //월경 주기란
    fun WhatMensturationCycle() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_what_menstrual_cycle.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatCycle.text = inputString
    }

    //정상적인 주기
    fun WhatNormalCycle() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_what_normal_cycle.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailNormalCycle.text = inputString
    }

    //생리 기간
    fun HowLongPeriod() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_how_long_period.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailNormalPeriod.text = inputString
    }

    //생리 몇살 때 시작
    fun WhenStartPeriod() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_when_age_start.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailStartAge.text = inputString
    }

    //어떤 증상?
    fun WhatSymptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_what_symptom.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSymptoms.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //월경 주기란
            layoutWhatCycle.setOnClickListener {
                if (layoutDetailWhatCycle.visibility == View.VISIBLE) {
                    layoutDetailWhatCycle.visibility = View.GONE
                    layoutBtnWhatCycle.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatCycle.visibility = View.VISIBLE
                    layoutBtnWhatCycle.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //정상적인 주기
            layoutNormalCycle.setOnClickListener {
                if (layoutDetailNormalCycle.visibility == View.VISIBLE) {
                    layoutDetailNormalCycle.visibility = View.GONE
                    layoutBtnNormalCycle.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailNormalCycle.visibility = View.VISIBLE
                    layoutBtnNormalCycle.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //정상적인 기간
            layoutNormalPeriod.setOnClickListener {
                if (layoutDetailNormalPeriod.visibility == View.VISIBLE) {
                    layoutDetailNormalPeriod.visibility = View.GONE
                    layoutBtnNormalPeriod.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailNormalPeriod.visibility = View.VISIBLE
                    layoutBtnNormalPeriod.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //몇살에 시작
            layoutStartAge.setOnClickListener {
                if (layoutDetailStartAge.visibility == View.VISIBLE) {
                    layoutDetailStartAge.visibility = View.GONE
                    layoutBtnStartAge.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailStartAge.visibility = View.VISIBLE
                    layoutBtnStartAge.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //어떤 증상
            layoutSymptoms.setOnClickListener {
                if (layoutDetailSymptoms.visibility == View.VISIBLE) {
                    layoutDetailSymptoms.visibility = View.GONE
                    layoutBtnSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailSymptoms.visibility = View.VISIBLE
                    layoutBtnSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}