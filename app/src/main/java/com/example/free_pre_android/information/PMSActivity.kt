package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPmsactivityBinding
import java.io.InputStream

class PMSActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPmsactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPmsactivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatPMS()
        SymptomsPMS()
        EasingSymptoms()
        EmotionalSymptomsPMS()
        PhysicalSymptomsPMS()
        CausesPMS()
        EasingSymptoms()
        viewBindingRun()
    }

    //pms 무엇
    fun WhatPMS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPms.text = inputString
    }

    //pms 증상
    fun SymptomsPMS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_symptoms.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPmsSymptoms.text = inputString
    }

    //감정적인 증상 - pms
    fun EmotionalSymptomsPMS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_emotional_symptoms.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailEmotionalSymptoms.text = inputString
    }

    //신체적인 증상 - pms
    fun PhysicalSymptomsPMS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_physical_symptoms.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPhysicalSymptoms.text = inputString
    }

    //pms 원인
    fun CausesPMS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_causes.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPmsCauses.text = inputString
    }

    //pms 완화 방법
    fun EasingSymptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pms_easing.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPmsEasing.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //pms 무엇?
            layoutWhatPms.setOnClickListener {
                if (layoutDetailWhatPms.visibility == View.VISIBLE) {
                    layoutDetailWhatPms.visibility = View.GONE
                    layoutBtnWhatPms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatPms.visibility = View.VISIBLE
                    layoutBtnWhatPms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //pms 증상
            layoutPmsSymptoms.setOnClickListener {
                if (layoutDetailPmsSymptoms.visibility == View.VISIBLE) {
                    layoutDetailPmsSymptoms.visibility = View.GONE
                    layoutBtnPmsSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPmsSymptoms.visibility = View.VISIBLE
                    layoutBtnPmsSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //감정적인 증상
            layoutEmotionalSymptoms.setOnClickListener {
                if (layoutDetailEmotionalSymptoms.visibility == View.VISIBLE) {
                    layoutDetailEmotionalSymptoms.visibility = View.GONE
                    layoutBtnEmotionalSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailEmotionalSymptoms.visibility = View.VISIBLE
                    layoutBtnEmotionalSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //신체적인 증상
            layoutPhysicalSymptoms.setOnClickListener {
                if (layoutDetailPhysicalSymptoms.visibility == View.VISIBLE) {
                    layoutDetailPhysicalSymptoms.visibility = View.GONE
                    layoutBtnPhysicalSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPhysicalSymptoms.visibility = View.VISIBLE
                    layoutBtnPhysicalSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //원인
            layoutPmsCauses.setOnClickListener {
                if (layoutDetailPmsCauses.visibility == View.VISIBLE) {
                    layoutDetailPmsCauses.visibility = View.GONE
                    layoutBtnPmsCauses.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPmsCauses.visibility = View.VISIBLE
                    layoutBtnPmsCauses.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //완화 방법
            layoutPmsEasing.setOnClickListener {
                if (layoutDetailPmsEasing.visibility == View.VISIBLE) {
                    layoutDetailPmsEasing.visibility = View.GONE
                    layoutBtnPmsEasing.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPmsEasing.visibility = View.VISIBLE
                    layoutBtnPmsEasing.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}