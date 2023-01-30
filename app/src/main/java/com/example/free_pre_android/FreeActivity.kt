package com.example.free_pre_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFreeBinding

class FreeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFreeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //처음 LeftEdit VERSION
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id,FreeHomeFragment())
            .commitAllowingStateLoss()

        //BottomNav - Home 부분
        viewBinding.btnHome.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,FreeHomeFragment())
                .commitAllowingStateLoss()

            //페이지 애니메이션 - 왼쪽에서 오른쪽으로 - 지워도 되고
            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right)
        }

        //BottomNav - 캘린더 부분
        viewBinding.btnCalendar.setOnClickListener {

        }

        //BottomNav - How To Use 부분
        viewBinding.btnHtu.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,HowToUseFragment())
                .commitAllowingStateLoss()

        }
    }
}