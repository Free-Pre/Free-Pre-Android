package com.example.free_pre_android

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentHowToUseBinding
import java.io.InputStream


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()
        howToUseText()
    }

    fun howToUseText(){
        val assetManager: AssetManager = resources.assets
        var inputStream: InputStream = assetManager.open("how_to_use.txt")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        viewBinding.textHowToUse.text = inputString
    }



    //접근성 - 확장함수 이용..(내용 수정 필요)
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
    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}