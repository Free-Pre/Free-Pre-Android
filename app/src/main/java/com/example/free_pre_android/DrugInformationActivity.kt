package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityDrugInformationBinding

//약 정보
class DrugInformationActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDrugInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDrugInformationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()


    }

    fun viewBindingRun(){
        viewBinding.run {
            btnOralContraceptives.setOnClickListener {
                startActivity(Intent(this@DrugInformationActivity,OralContraceptionActivity::class.java))
            }
            btnEmergencyContraceptives.setOnClickListener {
                startActivity(Intent(this@DrugInformationActivity,EmergencyContraceptiveActivity::class.java))
            }
        }
    }
}