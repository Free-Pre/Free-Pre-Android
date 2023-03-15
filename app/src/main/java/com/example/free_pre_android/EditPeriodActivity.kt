package com.example.free_pre_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityEditPeriodBinding

class EditPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEditPeriodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //초기화면
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id,EditPeriodStartFragment())
            .commitAllowingStateLoss()
        //startBtn 색 채우기(초기)
        viewBinding.btnStart.setBackgroundResource(R.drawable.style_clicked_period_border)
        //viewBinding.startBtn.setTextColor(application.resources.getColor(R.color.primary_dark))
        viewBinding.btnStart.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //start 버튼 누를 시
        viewBinding.btnStart.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,EditPeriodStartFragment())
                .commitAllowingStateLoss()

            //startBtn 색 채우기-start 버튼
            viewBinding.btnStart.setBackgroundResource(R.drawable.style_clicked_period_border)
            viewBinding.btnStart.setTextColor(Color.parseColor("#1A2A46"))   //dark

            //endBtn 색 없애기 - end 버튼
            viewBinding.btnEnd.setBackgroundResource(R.drawable.style_unclicked_period_border)
            viewBinding.btnEnd.setTextColor(Color.parseColor("#FDE3F4"))
        }
        //end 버튼 누를 시
        viewBinding.btnEnd.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,EditPeriodEndFragment())
                .commitAllowingStateLoss()

            //endBtn 색 채우기-end 버튼
            viewBinding.btnEnd.setBackgroundResource(R.drawable.style_clicked_period_border)
            viewBinding.btnEnd.setTextColor(Color.parseColor("#1A2A46"))   //dark

            //startBtn 색 없애기 -start 버튼
            viewBinding.btnStart.setBackgroundResource(R.drawable.style_unclicked_period_border)
            viewBinding.btnStart.setTextColor(Color.parseColor("#FDE3F4"))

        }

        viewBinding.btnSave.setOnClickListener {
            //월경일 입력 api 연결하기
            val intent = Intent(this,FreeActivity::class.java)
            startActivity(intent)
        }
    }
}


