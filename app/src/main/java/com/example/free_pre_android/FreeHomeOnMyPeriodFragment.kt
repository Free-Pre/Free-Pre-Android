package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomeOnMyPeriodBinding
import java.util.concurrent.TimeUnit


class FreeHomeOnMyPeriodFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeOnMyPeriodBinding

    //남은 일 수 불러오기
    val sharedTerm: SharedPreferences = requireActivity().getSharedPreferences("CycleTerm", Activity.MODE_PRIVATE)
    val term = sharedTerm.getInt("cycleTerm", 0)

    private val handler = Handler(Looper.getMainLooper())

    private var executionCount = 0 // 실행 횟수 체크를 위한 변수

    private val runnable = object : Runnable {
        override fun run() {
            // 실행할 코드 작성
            Log.d("ExampleFragment", "작업 실행됨")

            executionCount++

            if (executionCount < term) { // 실행 횟수가 5 미만일 때 다시 실행
                handler.postDelayed(this, TimeUnit.HOURS.toMillis(24))
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeOnMyPeriodBinding.inflate(layoutInflater)
        return viewBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnEditPeriod.setOnClickListener {
            startActivity(Intent(activity, EditPeriodListActivity::class.java))
        }

        handler.postDelayed(runnable, TimeUnit.HOURS.toMillis(0)) // 처음 실행

    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable) // Fragment가 제거될 때 handler를 제거
    }
}