package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomeLeftEditBinding


class FreeHomeLeftEditFragment : Fragment() {

    private lateinit var viewBinding: FragmentFreeHomeLeftEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeLeftEditBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnEditPeriod.setOnClickListener {
            startActivity(Intent(activity,EditPeriodListActivity::class.java))
        }

    }

}