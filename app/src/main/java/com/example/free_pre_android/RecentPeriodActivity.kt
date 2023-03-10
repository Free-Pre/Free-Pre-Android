package com.example.free_pre_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityRecentPeriodBinding

class RecentPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRecentPeriodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecentPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //초기화면
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id,RecentPeriodStartFragment())
            .commitAllowingStateLoss()
        //startBtn 색 채우기(초기)
        viewBinding.startBtn.setBackgroundResource(R.drawable.style_clicked_period_border)
        //viewBinding.startBtn.setTextColor(application.resources.getColor(R.color.primary_dark))
        viewBinding.startBtn.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //start 버튼 누를 시
        viewBinding.startBtn.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,RecentPeriodStartFragment())
                .commitAllowingStateLoss()

            //startBtn 색 채우기-start 버튼
            viewBinding.startBtn.setBackgroundResource(R.drawable.style_clicked_period_border)
            viewBinding.startBtn.setTextColor(Color.parseColor("#1A2A46"))   //dark

            //endBtn 색 없애기 - end 버튼
            viewBinding.endBtn.setBackgroundResource(R.drawable.style_unclicked_period_border)
            viewBinding.endBtn.setTextColor(Color.parseColor("#FDE3F4"))
        }
        //end 버튼 누를 시
        viewBinding.endBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,RecentPeriodEndFragment())
                .commitAllowingStateLoss()

            //endBtn 색 채우기-end 버튼
            viewBinding.endBtn.setBackgroundResource(R.drawable.style_clicked_period_border)
            viewBinding.endBtn.setTextColor(Color.parseColor("#1A2A46"))   //dark

            //startBtn 색 없애기 -start 버튼
            viewBinding.startBtn.setBackgroundResource(R.drawable.style_unclicked_period_border)
            viewBinding.startBtn.setTextColor(Color.parseColor("#FDE3F4"))

        }

        viewBinding.btnSave.setOnClickListener {
            //월경일 입력 api 연결하기
            val intent = Intent(this,FreeActivity::class.java)
            startActivity(intent)
        }
    }
}