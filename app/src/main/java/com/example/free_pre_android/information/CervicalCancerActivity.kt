package com.example.free_pre_android.information

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityCervicalCancerBinding
import java.io.InputStream

class CervicalCancerActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityCervicalCancerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =ActivityCervicalCancerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        whatCervicalCancer()
        Symptoms()
        RiskFactor()
        Prevention()
        viewBindingRun()
    }

    //자궁경부암 무엇
    fun whatCervicalCancer() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cervical_cancer_what.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailWhatCervicalCancer.text = inputString
    }

    //증상
    fun Symptoms() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cervical_cancer_symptom.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSymptoms.text = inputString
    }

    //위험요인
    fun RiskFactor() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cervical_cancer_risk_factor.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailRiskFactor.text = inputString
    }

    //예방
    fun Prevention() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("cervical_cancer_prevention.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPrevention.text = inputString
    }



    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //자궁경부암 무엇
            layoutWhatCervicalCancer.setOnClickListener {
                if (layoutDetailWhatCervicalCancer.visibility == View.VISIBLE) {
                    layoutDetailWhatCervicalCancer.visibility = View.GONE
                    layoutBtnSymptoms.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailWhatCervicalCancer.visibility = View.VISIBLE
                    layoutBtnSymptoms.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //증상
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

            //위험요인
            layoutRiskFactor.setOnClickListener {
                if (layoutDetailRiskFactor.visibility == View.VISIBLE) {
                    layoutDetailRiskFactor.visibility = View.GONE
                    layoutBtnRiskFactor.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailRiskFactor.visibility = View.VISIBLE
                    layoutBtnRiskFactor.animate().apply {
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