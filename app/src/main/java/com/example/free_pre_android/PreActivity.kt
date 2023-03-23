package com.example.free_pre_android

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPreBinding
import com.example.free_pre_android.information.PreMenstruationKnowledgeActivity
import com.example.free_pre_android.information.PreProductActivity
import com.example.free_pre_android.information.WhatMenstruationActivity

class PreActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPreBinding
    var btnChangeHeightClicked = false

    //Int to dp - 나중에 시도해보기
    /*val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()


        //https://www.geeksforgeeks.org/how-to-change-textview-size-programmatically-in-android/
        viewBinding.linearTop.setOnClickListener {
            slideView(viewBinding.linearTop,viewBinding.linearTop.layoutParams.height,630)
        }
    }


    /*클릭할 때마다 뷰 높이 조절 */
    fun slideView(view: View, currentHeight:Int, newHeight:Int){
        val slideAnimator = ValueAnimator.ofInt(currentHeight,newHeight).setDuration(500)

        slideAnimator.addUpdateListener {
            val value :Int
            value = it.getAnimatedValue() as Int
            view.layoutParams.height = value.toInt()
            view.requestLayout()
        }

        val animationSet:AnimatorSet = AnimatorSet()
        animationSet.setInterpolator(AccelerateDecelerateInterpolator())
        animationSet.play(slideAnimator)
        animationSet.start()
    }
    fun viewBindingRun(){
        viewBinding.run {
            //Free로 이동
            btnGoFree.setOnClickListener {
                startActivity(Intent(this@PreActivity,FreeActivity::class.java))
            }
            //생리가 무엇인지
            btnWhatIsMenstruation.setOnClickListener {
                startActivity(Intent(this@PreActivity, WhatMenstruationActivity::class.java))
            }
            //생리에 관한 정보
            btnKnowledgeMenstruation.setOnClickListener {
                startActivity(Intent(this@PreActivity, PreMenstruationKnowledgeActivity::class.java))
            }
            //생리용품
            btnMenstrualProducts.setOnClickListener {
                startActivity(Intent(this@PreActivity, PreProductActivity::class.java))
            }
            //faq
            btnFaq.setOnClickListener {
                startActivity(Intent(this@PreActivity,PreFaqActivity::class.java))
            }
        }
    }
}