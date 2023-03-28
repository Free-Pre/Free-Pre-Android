package com.example.free_pre_android

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomeOverStartBinding


class FreeHomeOverStartFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeOverStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeOverStartBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnStartPeriod.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.top_over_start, FreeHomeOnMyPeriodFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            Log.d("TopOverStart", "월경 진행 날짜 시작")
        }

        //남은 일 수 불러오기
        val sharedOverDiff: SharedPreferences = requireActivity().getSharedPreferences("OverDiff", Activity.MODE_PRIVATE)
        viewBinding.textOverStart.text = sharedOverDiff.getLong("overDiff", 0L).toString()+" DAYS OVER"

    }

}