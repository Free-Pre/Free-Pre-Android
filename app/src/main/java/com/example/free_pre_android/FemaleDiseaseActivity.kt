package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFemaleDiseaseBinding

class FemaleDiseaseActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFemaleDiseaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFemaleDiseaseBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()
    }

    fun viewBindingRun(){
        viewBinding.run {
            //질염 페이지로 이동
            btnVaginitis.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,VaginitisActivity::class.java))
            }
            //다낭성 난소 증후군 페이지로 이동
            btnPcos.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,PcosActivity::class.java))
            }
        }
    }
}