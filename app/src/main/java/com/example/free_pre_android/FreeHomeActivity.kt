package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFreeHomeBinding

class FreeHomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportFragmentManager.setupForAccessibility()      //확장 함수

        viewBinding = ActivityFreeHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //처음 LeftEdit VERSION
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.bottomFrameFragment.id,FreeHomeLeftEditFragment())
            .commitAllowingStateLoss()

        //BottomNav - Home 부분
        viewBinding.btnHome.setOnClickListener {
            //Home 부분을 액티비티로 만들었으니까
            val intent = Intent(this,FreeHomeActivity::class.java)
            startActivity(intent)

            //페이지 애니메이션 - 왼쪽에서 오른쪽으로
            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right)
        }

        //BottomNav - 캘린더 부분
        viewBinding.btnCalendar.setOnClickListener {

        }

        //BottomNav - How To Use 부분
        viewBinding.btnHtu.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.bottomFrameFragment.id,HowToUseFragment())
                .commitAllowingStateLoss()

            //focus 부분 되긴 되는데 효율성 떨어짐.. -> 확장 함수를 이용해서 바꿔보기
            //Home 부분을 fragment로 바꾸면 필요없어진다.
            viewBinding.bottomFrameFragment.importantForAccessibility=View.IMPORTANT_FOR_ACCESSIBILITY_YES                   //focus 되도록
            viewBinding.freeHomeScrollView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS  //focus 안되게
            viewBinding.freeHomeTopFrame.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS    //focus 안되게
        }



    }

    //android accessiblity 확장 함수로 정의할 필요 있음..
    /*fun FragmentManager.setupForAccessibility(){
        addOnBackStackChangedListener {
            val lastFragmentWithView = fragments.last { it.view != null }
            for(fragment in fragments){
                if(fragment == lastFragmentWithView){
                    fragment.view?.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                }else{
                    fragment.view?.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
                }
            }
        }
    }*/

}

