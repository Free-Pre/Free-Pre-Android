package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityKnowledgeOfMenstruationBinding

class KnowledgeOfMenstruationActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityKnowledgeOfMenstruationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityKnowledgeOfMenstruationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()

    }


    fun viewBindingRun(){
        viewBinding.run {
            //산부인과 클릭시
            textObGyn.setOnClickListener {
                startActivity(Intent(this@KnowledgeOfMenstruationActivity,GoogleMapActivity::class.java))
            }
            textDrugInformation.setOnClickListener {
                startActivity(Intent(this@KnowledgeOfMenstruationActivity,DrugInformationActivity::class.java))
            }
            textPms.setOnClickListener {
                startActivity(Intent(this@KnowledgeOfMenstruationActivity,PMSActivity::class.java))
            }
            textFemaleDisease.setOnClickListener {
                startActivity(Intent(this@KnowledgeOfMenstruationActivity,FemaleDiseaseActivity::class.java))
            }
        }


    }
}