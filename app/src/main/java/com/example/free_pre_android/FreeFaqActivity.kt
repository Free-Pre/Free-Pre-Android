package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFreeFaqBinding
import java.io.InputStream

class FreeFaqActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFreeFaqBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        NoMenstruation()
        FallOutProudct()
        SmallPeriod()
        MuchPeriod()
        PreventionCervicalCancer()
        viewBindingRun()
    }

    //생리를 하지 않아요
    fun NoMenstruation() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("free_faq_no_menstruation.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailNoMenstruation.text = inputString
    }

    //월경컵, 탐폰 빠지지 않아요?
    fun FallOutProudct() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("free_faq_fall_out_product.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailFallOutProduct.text = inputString
    }
    //생리 양이 너무 적어요
    fun SmallPeriod() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("free_faq_small_period.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailSmallPeriod.text = inputString
    }
    //생리 양이 너무 많아요
    fun MuchPeriod() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("free_faq_much_period.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailMuchPeriod.text = inputString
    }
    //자궁경부암 예방 주사 필수입니까?
    fun PreventionCervicalCancer() {
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("free_faq_prevention_cervical_cancer.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textDetailPreventionCervicalCancer.text = inputString
    }





    //viewBinding 동작
    fun viewBindingRun() {
        viewBinding.run {
            //생리를 하지 않아요
            layoutNoMenstruation.setOnClickListener {
                if (layoutDetailNoMenstruation.visibility == View.VISIBLE) {
                    layoutDetailNoMenstruation.visibility = View.GONE
                    layoutBtnNoMenstruation.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailNoMenstruation.visibility = View.VISIBLE
                    layoutBtnNoMenstruation.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //월경컵, 탐폰 빠지지 않아요?
            layoutFallOutProduct.setOnClickListener {
                if (layoutDetailFallOutProduct.visibility == View.VISIBLE) {
                    layoutDetailFallOutProduct.visibility = View.GONE
                    layoutBtnFallOutProduct.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailFallOutProduct.visibility = View.VISIBLE
                    layoutBtnFallOutProduct.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //생리 양이 너무 적어요
            layoutSmallPeriod.setOnClickListener {
                if (layoutDetailSmallPeriod.visibility == View.VISIBLE) {
                    layoutDetailSmallPeriod.visibility = View.GONE
                    layoutBtnSmallPeriod.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailSmallPeriod.visibility = View.VISIBLE
                    layoutBtnSmallPeriod.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //생리 양이 너무 많아요
            layoutMuchPeriod.setOnClickListener {
                if (layoutDetailMuchPeriod.visibility == View.VISIBLE) {
                    layoutDetailMuchPeriod.visibility = View.GONE
                    layoutBtnMuchPeriod.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailMuchPeriod.visibility = View.VISIBLE
                    layoutBtnMuchPeriod.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }

            //자궁경부암 백신 꼭 맞아야 하나요?
            layoutPreventionCervicalCancer.setOnClickListener {
                if (layoutDetailPreventionCervicalCancer.visibility == View.VISIBLE) {
                    layoutDetailPreventionCervicalCancer.visibility = View.GONE
                    layoutBtnPreventionCervicalCancer.animate().apply {
                        duration = 300
                        rotation(0f)
                    }
                } else {
                    layoutDetailPreventionCervicalCancer.visibility = View.VISIBLE
                    layoutBtnPreventionCervicalCancer.animate().apply {
                        duration = 300
                        rotation(180f)    //화살표 180회전
                    }
                }
            }


        }
    }
}