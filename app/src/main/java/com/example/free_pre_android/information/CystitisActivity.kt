package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityCystitisBinding
import java.io.InputStream

class CystitisActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCystitisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCystitisBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatCystitis()
        Symptoms()
        WhenDoctor()
        viewBindingRun()
    }

    //방광염 무엇
    fun WhatCystitis() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cystitis_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatCystitis.text = inputString
    }

    //방광염 증상
    fun Symptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cystitis_symptom.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSymptoms.text = inputString
    }

    //의사를 만나야 할 때
    fun WhenDoctor() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cystitis_when_doctor.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhenDoctor.text = inputString
    }


    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //방광염 무엇
            layoutWhatCystitis.setOnClickListener {
                if (layoutDetailWhatCystitis.visibility == View.VISIBLE) {
                    layoutDetailWhatCystitis.visibility = View.GONE
                    layoutBtnWhatCystitis.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatCystitis.visibility = View.VISIBLE
                    layoutBtnWhatCystitis.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //방광염 증상
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

            //의사를 만나야 할 때
            layoutWhenDoctor.setOnClickListener {
                if (layoutDetailWhenDoctor.visibility == View.VISIBLE) {
                    layoutDetailWhenDoctor.visibility = View.GONE
                    layoutBtnWhenDoctor.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhenDoctor.visibility = View.VISIBLE
                    layoutBtnWhenDoctor.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

        }
    }
}