package com.example.free_pre_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.free_pre_android.databinding.FragmentRecentPeroidStartBinding


class RecentPeriodStartFragment : Fragment() {
    private lateinit var viewBinding: FragmentRecentPeroidStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRecentPeroidStartBinding.inflate(layoutInflater)
        return viewBinding.root
    }


}