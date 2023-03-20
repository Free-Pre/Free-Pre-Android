package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityBreastCancerBinding
import java.io.InputStream

class BreastCancerActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityBreastCancerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBreastCancerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatBreastCancer()
        Symptoms()
        WhenDoctor()
        Prevention()
        viewBindingRun()


    }

    //유방암 무엇
    fun WhatBreastCancer() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("breast_cancer_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatBreastCancer.text = inputString
    }

    //유방암 증상
    fun Symptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("breast_cancer_symptom.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSymptoms.text = inputString
    }

    //의사를 만나야 할 때
    fun WhenDoctor() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("breast_cancer_when_doctor.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhenDoctor.text = inputString
    }

    //예방
    fun Prevention() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("breast_cancer_prevention.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPrevention.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //유방암 뭔지
            layoutWhatBreastCancer.setOnClickListener {
                if (layoutDetailWhatBreastCancer.visibility == View.VISIBLE) {
                    layoutDetailWhatBreastCancer.visibility = View.GONE
                    layoutBtnWhatBreastCancer.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatBreastCancer.visibility = View.VISIBLE
                    layoutBtnWhatBreastCancer.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //유방암 증상
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

            //의사를 만나야할 대
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

            //예방
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