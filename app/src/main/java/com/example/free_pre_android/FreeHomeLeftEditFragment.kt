package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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

        //남은 일 수 불러오기
        val sharedDayDiff: SharedPreferences = requireActivity().getSharedPreferences("DayDiff", Activity.MODE_PRIVATE)
        viewBinding.textLeftDays.text = sharedDayDiff.getLong("dayDiff", 0L).toString()+" DAYS LEFT"

    }

}