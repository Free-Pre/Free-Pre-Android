package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPreFaqBinding
import java.io.InputStream

class PreFaqActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityPreFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreFaqBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        HowMuchBleed()
        WhatSecondary()
        OutsideMenstruate()
        viewBindingRun()
    }

    //얼마동안 피 흘리는지
    fun HowMuchBleed() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_faq_how_much_bleed.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailBleed.text = inputString
    }

    //2차 성징
    fun WhatSecondary() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_faq_secondary.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatSecondary.text = inputString
    }

    //밖에서 생리할 시 대비
    fun OutsideMenstruate() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_faq_outside_menstruate.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailOutsideMenstruate.text = inputString
    }




    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //얼마동안 피 흘리는지
            layoutBleed.setOnClickListener {
                if (layoutDetailBleed.visibility == View.VISIBLE) {
                    layoutDetailBleed.visibility = View.GONE
                    layoutBtnBleed.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailBleed.visibility = View.VISIBLE
                    layoutBtnBleed.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //2차 성징
            layoutWhatSecondary.setOnClickListener {
                if (layoutDetailWhatSecondary.visibility == View.VISIBLE) {
                    layoutDetailWhatSecondary.visibility = View.GONE
                    layoutBtnWhatSecondary.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatSecondary.visibility = View.VISIBLE
                    layoutBtnWhatSecondary.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //밖에서 생리할 시 대비
            layoutOutsideMenstruate.setOnClickListener {
                if (layoutDetailOutsideMenstruate.visibility == View.VISIBLE) {
                    layoutDetailOutsideMenstruate.visibility = View.GONE
                    layoutBtnOutsideMenstruate.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailOutsideMenstruate.visibility = View.VISIBLE
                    layoutBtnOutsideMenstruate.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}