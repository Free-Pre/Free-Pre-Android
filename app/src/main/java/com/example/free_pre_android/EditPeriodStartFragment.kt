package com.example.free_pre_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentEditPeriodStartBinding

class EditPeriodStartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEditPeriodStartBinding.inflate(inflater, container, false)
        
        return binding.root
    }

}