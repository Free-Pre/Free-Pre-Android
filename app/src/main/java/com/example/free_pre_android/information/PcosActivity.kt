package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPcosBinding
import java.io.InputStream

class PcosActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPcosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPcosBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatPCOS()
        CausesPCOS()
        SymptomsPCOS()
        ComplicationsPCOS()
        viewBindingRun()
    }

    //다낭성 난소 증후군 무엇
    fun WhatPCOS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pcos_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPcos.text = inputString
    }

    //원인
    fun CausesPCOS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pcos_causes.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPcosCauses.text = inputString
    }

    //증상
    fun SymptomsPCOS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pcos_symptoms.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPcosSymptoms.text = inputString
    }

    //합병증
    fun ComplicationsPCOS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pcos_complications.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailComplications.text = inputString
    }




    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //다낭성 난소 증후군 무엇
            layoutWhatPcos.setOnClickListener {
                if (layoutDetailWhatPcos.visibility == View.VISIBLE) {
                    layoutDetailWhatPcos.visibility = View.GONE
                    layoutBtnWhatPcos.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatPcos.visibility = View.VISIBLE
                    layoutBtnWhatPcos.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //원인
            layoutPcosCauses.setOnClickListener {
                if (layoutDetailPcosCauses.visibility == View.VISIBLE) {
                    layoutDetailPcosCauses.visibility = View.GONE
                    layoutBtnPcosCauses.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPcosCauses.visibility = View.VISIBLE
                    layoutBtnPcosCauses.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //증상
            layoutPcosSymptoms.setOnClickListener {
                if (layoutDetailPcosSymptoms.visibility == View.VISIBLE) {
                    layoutDetailPcosSymptoms.visibility = View.GONE
                    layoutBtnPcosSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPcosSymptoms.visibility = View.VISIBLE
                    layoutBtnPcosSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //합병증
            layoutComplications.setOnClickListener {
                if (layoutDetailComplications.visibility == View.VISIBLE) {
                    layoutDetailComplications.visibility = View.GONE
                    layoutBtnComplications.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailComplications.visibility = View.VISIBLE
                    layoutBtnComplications.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}