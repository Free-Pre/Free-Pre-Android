package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomeBinding


class FreeHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFreeHomeBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBindingRun()
    }

    fun viewBindingRun(){
        viewBinding.run {
            btnCamera.setOnClickListener {
                startActivity(Intent(activity ,CameraActivity::class.java))   //getActivity or context -> fragment에서 this 불가
            }
            btnKnowledge.setOnClickListener {
                startActivity(Intent(activity,KnowledgeOfMenstruationActivity::class.java))
            }
            btnProducts.setOnClickListener {
                startActivity(Intent(activity, ProductsActivity::class.java))
            }
            btnFemaleDisease.setOnClickListener {
                startActivity(Intent(activity,FemaleDiseaseActivity::class.java))
            }
            btnFaq.setOnClickListener {
                startActivity(Intent(activity,FreeFaqActivity::class.java))
            }
        }
    }

}
