package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPreProductBinding
import java.io.InputStream

class PreProductActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPreProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreProductBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Pad()
        Tampon()
        Cup()
        viewBindingRun()
    }

    //생리대
    fun Pad() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_product_pad.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPad.text = inputString
    }

    //탐폰
    fun Tampon() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_product_tampon.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailTampon.text = inputString
    }

    //생리컵
    fun Cup() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("pre_product_cup.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailCup.text = inputString
    }




    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //생리대
            layoutPad.setOnClickListener {
                if (layoutDetailPad.visibility == View.VISIBLE) {
                    layoutDetailPad.visibility = View.GONE
                    layoutBtnPad.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPad.visibility = View.VISIBLE
                    layoutBtnPad.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //탐폰
            layoutTampon.setOnClickListener {
                if (layoutDetailTampon.visibility == View.VISIBLE) {
                    layoutDetailTampon.visibility = View.GONE
                    layoutBtnTampon.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailTampon.visibility = View.VISIBLE
                    layoutBtnTampon.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //생리컵
            layoutCup.setOnClickListener {
                if (layoutDetailCup.visibility == View.VISIBLE) {
                    layoutDetailCup.visibility = View.GONE
                    layoutBtnCup.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailCup.visibility = View.VISIBLE
                    layoutBtnCup.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}