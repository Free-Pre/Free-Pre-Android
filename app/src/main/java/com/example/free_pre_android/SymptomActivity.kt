package com.example.free_pre_android

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivitySymptomBinding

class SymptomActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySymptomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySymptomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()
    }

    fun viewBindingRun(){
        viewBinding.run {
            textHeadache.setOnClickListener {
                textHeadache.setBackgroundResource(R.color.primary_light)
                textHeadache.setTextColor(Color.parseColor("#1A2A46"))
            }
            textBreastPain.setOnClickListener {
                textBreastPain.setBackgroundResource(R.color.primary_light)
                textBreastPain.setTextColor(Color.parseColor("#1A2A46"))
            }
            textDepressed.setOnClickListener {
                textDepressed.setBackgroundResource(R.color.primary_light)
                textDepressed.setTextColor(Color.parseColor("#1A2A46"))
            }
            textFatigue.setOnClickListener {
                textFatigue.setBackgroundResource(R.color.primary_light)
                textFatigue.setTextColor(Color.parseColor("#1A2A46"))
            }
            textAnxious.setOnClickListener {
                textAnxious.setBackgroundResource(R.color.primary_light)
                textAnxious.setTextColor(Color.parseColor("#1A2A46"))
            }
            textSwollenBreast.setOnClickListener {
                textSwollenBreast.setBackgroundResource(R.color.primary_light)
                textSwollenBreast.setTextColor(Color.parseColor("#1A2A46"))
            }
            textAppetiteChange.setOnClickListener {
                textAppetiteChange.setBackgroundResource(R.color.primary_light)
                textAppetiteChange.setTextColor(Color.parseColor("#1A2A46"))
            }
            textConstipation.setOnClickListener {
                textConstipation.setBackgroundResource(R.color.primary_light)
                textConstipation.setTextColor(Color.parseColor("#1A2A46"))
            }
            textDiarrhea.setOnClickListener {
                textDiarrhea.setBackgroundResource(R.color.primary_light)
                textDiarrhea.setTextColor(Color.parseColor("#1A2A46"))
            }
            textDema.setOnClickListener {
                textDema.setBackgroundResource(R.color.primary_light)
                textDema.setTextColor(Color.parseColor("#1A2A46"))
            }
            textBackPain.setOnClickListener {
                textBackPain.setBackgroundResource(R.color.primary_light)
                textBackPain.setTextColor(Color.parseColor("#1A2A46"))
            }






        }
    }
}