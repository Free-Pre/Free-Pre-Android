package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentCalendarBinding
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter

class CalendarFragment : Fragment(){
    private lateinit var viewBinding : FragmentCalendarBinding
    private lateinit var calendarView: MaterialCalendarView;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = viewBinding.calendarView

        viewBindingRun()
        CustomCalendar()




        /*calenderView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            //날짜 선택시 원하는 동작

       }*/
    }

    //클릭시 이동
    fun viewBindingRun(){
        viewBinding.run {
            button.setOnClickListener {
                startActivity(Intent(activity,SymptomActivity::class.java))
            }
            btnSetting.setOnClickListener {
                startActivity(Intent(activity,FreeSettingActivity::class.java))
            }
        }
    }
    fun CustomCalendar() {
        //달, 월 영어로 표시
        calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

    }


}