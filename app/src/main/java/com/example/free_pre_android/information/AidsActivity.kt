package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityAidsBinding
import java.io.InputStream

class AidsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAidsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAidsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatAIDS()
        Symptoms()
        viewBindingRun()
    }

    //에이즈 무엇
    fun WhatAIDS() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("aids_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatAids.text = inputString
    }

    //에이즈 증상
    fun Symptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("aids_symptom.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSymptoms.text = inputString
    }





    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //에이즈 무엇
            layoutWhatAids.setOnClickListener {
                if (layoutDetailWhatAids.visibility == View.VISIBLE) {
                    layoutDetailWhatAids.visibility = View.GONE
                    layoutBtnWhatAids.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatAids.visibility = View.VISIBLE
                    layoutBtnWhatAids.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //에이즈 증상
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