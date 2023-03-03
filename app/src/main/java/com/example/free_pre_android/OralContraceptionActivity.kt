package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityOralContraceptivesBinding
import java.io.InputStream

class OralContraceptionActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityOralContraceptivesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOralContraceptivesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        WhatOralContraception()
        HowWorkOralContraception()
        Advantage()
        Disadvantage()
        HowToTake()
        viewBindingRun()
    }


    //경구피임약 무엇
    fun WhatOralContraception() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("oral_contraceptives_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatPill.text = inputString
    }

    //호르몬 어떻게 작용
    fun HowWorkOralContraception() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("oral_contraceptives_how_work.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailHowWork.text = inputString
    }

    //이점
    fun Advantage() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("oral_contraceptives_advantages.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailAdvantagePill.text = inputString
    }

    //단점
    fun Disadvantage() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("oral_contraceptives_disadvantages.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailDisadvantagePill.text = inputString
    }

    //복용법
    fun HowToTake() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("oral_contraceptives_taking.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailTakePill.text = inputString
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

            //호르몬 어떻게 작용?
            layoutHowWork.setOnClickListener {
                if (layoutDetailHowWork.visibility == View.VISIBLE) {
                    layoutDetailHowWork.visibility = View.GONE
                    layoutBtnHowWork.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailHowWork.visibility = View.VISIBLE
                    layoutBtnHowWork.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //이점
            layoutAdvantagePill.setOnClickListener {
                if (layoutDetailAdvantagePill.visibility == View.VISIBLE) {
                    layoutDetailAdvantagePill.visibility = View.GONE
                    layoutBtnAdvantagePill.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailAdvantagePill.visibility = View.VISIBLE
                    layoutBtnAdvantagePill.animate().apply {
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

            //복용법
            layoutTakePill.setOnClickListener {
                if (layoutDetailTakePill.visibility == View.VISIBLE) {
                    layoutDetailTakePill.visibility = View.GONE
                    layoutBtnTakePill.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailTakePill.visibility = View.VISIBLE
                    layoutBtnTakePill.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }

//돋보기 기능 있으면 좋을 듯?
}
