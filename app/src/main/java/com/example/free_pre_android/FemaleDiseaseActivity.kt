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
            //유방암 페이지
            textBreastCancer.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,BreastCancerActivity::class.java))
            }
            //자궁경부암 페이지
            textCervicalCancer.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,CervicalCancerActivity::class.java))
            }
            //질염 페이지로 이동
            textVaginitis.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,VaginitisActivity::class.java))
            }
            //다낭성 난소 증후군 페이지로 이동
            textPcos.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,PcosActivity::class.java))
            }
            //에이즈 페이지
            textAids.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,AidsActivity::class.java))
            }
            //방광염 페이지
            textCystitis.setOnClickListener {
                startActivity(Intent(this@FemaleDiseaseActivity,CystitisActivity::class.java))
            }
        }
    }
}