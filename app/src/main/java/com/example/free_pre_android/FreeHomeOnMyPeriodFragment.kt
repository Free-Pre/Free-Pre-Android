package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentFreeHomeOnMyPeriodBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class FreeHomeOnMyPeriodFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeOnMyPeriodBinding


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

        //남은 일 수 불러오기
        val sharedTerm: SharedPreferences = requireActivity().getSharedPreferences("CycleTerm", Activity.MODE_PRIVATE)
        val term = sharedTerm.getInt("cycleTerm", 0)
        Log.d("SharedTerm",term.toString())


        //최신 월경 시작 불러오기
        val sharedStartDate: SharedPreferences = requireActivity().getSharedPreferences("SharedStartDate", Activity.MODE_PRIVATE)
        val startDateString = sharedStartDate.getString("sharedStartDate", "there's no sharedStartDate")
        Log.d("startDateString",startDateString.toString())


        //최신 월경 끝 불러오기
        val sharedEndDate: SharedPreferences = requireActivity().getSharedPreferences("SharedEndDate", Activity.MODE_PRIVATE)
        val endDateString = sharedEndDate.getString("sharedEndDate", "there's no sharedEndDate")
        Log.d("endDateString",endDateString.toString())

        //현재날짜
        val pattern = "yyyy-MM-dd" // 날짜 형식 지정
        val simpleDateFormat = SimpleDateFormat(pattern)

        val currentDate = Date() // 현재 날짜와 시간을 담은 Date 객체 생성
        val currentDateString = simpleDateFormat.format(currentDate) // 현재 날짜를 지정한 형식의 문자열로 변환

        val startDate = simpleDateFormat.parse(startDateString) // 문자열로 된 startDate를 Date 타입으로 변환
        val endDate = simpleDateFormat.parse(endDateString) // 문자열로 된 endDate를 Date 타입으로 변환

        // 현재 날짜가 startDate와 endDate 사이에 있는지 확인
        if (currentDate.after(startDate) && currentDate.before(endDate)) {
            //val diff = endDate.time - currentDate.time // endDate와 현재 날짜 간의 시간 차이 계산
            val diff = currentDate.time - startDate.time    //현재 날짜 - 시작날짜
            val days = TimeUnit.MILLISECONDS.toDays(diff)+1 // 시간 차이를 일 수로 변환
            val message = "월경 며칠 차: ${days}일" // 메시지 생성
            Log.d("PeriodTracker", message) // 로그 출력 또는 화면에 표시
            viewBinding.textPeriodDay.text = "Day" + " ${days}"      //Day1, Day2...
        }




        //today_date - 최근 start_date +1


        //n일차 계산 - 오늘 날짜 - 최근 start_date+1
        //오늘 날짜, start_date 날짜 변수 선언
        //start_date 날짜 homeFragment에서 가져오기
        //텍스트 띄우기




    }

}