package com.example.free_pre_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.free_pre_android.databinding.FragmentFreeHomeLeftStartBinding


class FreeHomeLeftStartFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeLeftStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeLeftStartBinding.inflate(layoutInflater)
        return viewBinding.root
    }

}