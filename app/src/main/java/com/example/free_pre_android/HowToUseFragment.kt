package com.example.free_pre_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import androidx.fragment.app.FragmentManager
import com.example.free_pre_android.databinding.FragmentHowToUseBinding


class HowToUseFragment : Fragment() {
    private lateinit var viewBinding: FragmentHowToUseBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //childFragmentManager.setupForAccessibility()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHowToUseBinding.inflate(layoutInflater)
        return viewBinding.root
    }


    //접근성 - 확장함수 이용..(수정 필요)
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