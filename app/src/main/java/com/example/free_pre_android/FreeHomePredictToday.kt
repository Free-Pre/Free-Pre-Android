package com.example.free_pre_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomePredictTodayBinding


class FreeHomePredictToday : Fragment() {

    private lateinit var viewBinding: FragmentFreeHomePredictTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomePredictTodayBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnStartPeriod.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.text_predict_today, FreeHomeOnMyPeriodFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            Log.d("TodayPeriod", "월경 진행 날짜 시작")
        }

    }


}