package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityTamponBinding
import java.io.InputStream

class TamponActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTamponBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTamponBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatTampon()
        HowToUseTampon()
        ChangeTampon()
        PrecautionTampon()
        viewBindingRun()
    }

    //탐폰 무엇
    fun WhatTampon() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("tampon_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatTampon.text = inputString
    }

    //탐폰 사용법
    fun HowToUseTampon() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("tampon_how.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailTamponHow.text = inputString
    }

    //탐폰 교체
    fun ChangeTampon() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("tampon_change.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailTamponChange.text = inputString
    }

    //탐포 주의 사항
    fun PrecautionTampon() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("tampon_precaution.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPrecaution.text = inputString
    }




    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //탐폰 무엇?
            layoutWhatTampon.setOnClickListener {
                if (layoutDetailWhatTampon.visibility == View.VISIBLE) {
                    layoutDetailWhatTampon.visibility = View.GONE
                    layoutBtnWhatTampon.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatTampon.visibility = View.VISIBLE
                    layoutBtnWhatTampon.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //탐폰 사용법
            layoutTamponHow.setOnClickListener {
                if (layoutDetailTamponHow.visibility == View.VISIBLE) {
                    layoutDetailTamponHow.visibility = View.GONE
                    layoutBtnTamponHow.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailTamponHow.visibility = View.VISIBLE
                    layoutBtnTamponHow.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //탐폰 교체
            layoutTamponChange.setOnClickListener {
                if (layoutDetailTamponChange.visibility == View.VISIBLE) {
                    layoutDetailTamponChange.visibility = View.GONE
                    layoutBtnTamponChange.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailTamponChange.visibility = View.VISIBLE
                    layoutBtnTamponChange.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //탐폰 주의사항
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
