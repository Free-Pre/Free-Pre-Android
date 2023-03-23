package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.free_pre_android.data.CalendarCheckResultDTO
import com.example.free_pre_android.databinding.FragmentCalendarBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarFragment : Fragment(){
    private lateinit var viewBinding : FragmentCalendarBinding
    private lateinit var calendarView: MaterialCalendarView
    var user_email:String =""
    var user_month:String =""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = viewBinding.calendarView

        //이메일 불러오기
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        user_email = sharedPreferences.getString("emailKey","there's no email").toString()               //로그인 되어있는 유저의 이메일
        user_month = viewBinding.calendarView.currentDate.month.toString()                    //현재달

        Log.d("TestEmail","${user_email}")    //잘 들어옴
        Log.d("TestEmail","${user_email}")

        //user_email = "example@test.com"
        //user_month ="202203"

        viewBindingRun()
        CustomCalendar()
        viewBindingView()
        CalendarCheck("${user_email}","${user_month}")



    }

    fun viewBindingView(){
        viewBinding.calendarView.leftArrow.setTint(ContextCompat.getColor(requireActivity(), R.color.primary_light))
        viewBinding.calendarView.rightArrow.setTint(ContextCompat.getColor(requireActivity(), R.color.primary_light))

        viewBinding.calendarView.selectedDate = CalendarDay.today()     //오늘 날짜 표시

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


    //레트로핏
    fun CalendarCheck(
        email:String,
        month:String,
    ){
        // API 호출
        RetrofitBuilder.calendarApi.calendarCheck(email, month).enqueue(object : Callback<CalendarCheckResultDTO> {
            override fun onResponse(call: Call<CalendarCheckResultDTO>, response: Response<CalendarCheckResultDTO>) {
                Log.d("calendar11",response.body().toString())
                if (response.isSuccessful) {
                    // 응답이 성공적으로 왔을 때 처리할 내용
                    val result: CalendarCheckResultDTO? = response.body()
                    result?.let {
                        // result 객체를 이용하여 UI 업데이트 등의 작업 수행
                        Log.d("calendarTest","연결성공")
                    }
                } else {
                    // 응답이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.d("calendarTest","연결미스")
                }
            }

            override fun onFailure(call: Call<CalendarCheckResultDTO>, t: Throwable) {
                // 네트워크 오류 등으로 인해 요청이 실패한 경우
                // 에러 메시지 출력 등의 처리 수행
                Log.d("calendarTest","연결안됨.")
            }
        })
    }








    // 디폴트 커스텀 함수
    class DefaultDecorator(context: CalendarFragment) : DayViewDecorator {
        val defaultDrawable = context.resources.getDrawable(R.drawable.style_calendar_cycle)

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return true
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(defaultDrawable)
        }
    }
    // 오늘 커스텀 함수
    class TodayDecorator(context: CalendarFragment, stayoutDays: MutableList<Int>?) : DayViewDecorator {
        val stayoutDays = stayoutDays

        override fun shouldDecorate(day: CalendarDay?): Boolean { // 커스텀 여부 반환
            return day?.equals(CalendarDay.today())!!
        }

        override fun decorate(view: DayViewFacade?) { // 커스텀 설정
            if (stayoutDays?.contains(CalendarDay.today().day) == true) view?.addSpan(object :
                ForegroundColorSpan(Color.parseColor("#FFFFFFFF")) {})
            else view?.addSpan(object : ForegroundColorSpan(Color.parseColor("#FFFFA114")) {})
        }
    }
    // 점호일 커스텀 함수 -> 사용자가 누른 날짜 커스텀
    class RollcallDecorator(context: CalendarFragment, rollcallDays: MutableList<Int>?, month: Int) :
        DayViewDecorator {
        val rollcallDrawable = context.resources.getDrawable(R.drawable.style_calendar_cycle)
        val rollcallDays = rollcallDays
        val month = month

        override fun shouldDecorate(day: CalendarDay?): Boolean { // 커스텀 여부 반환
            return rollcallDays?.contains(day!!.day) == true && day?.month == month
        }

        override fun decorate(view: DayViewFacade?) { // 커스텀 설정
            view?.setBackgroundDrawable(rollcallDrawable)
        }
    }

    // 외박 신청일 커스텀 함수 -> 월경 기간 커스텀
    class StayoutDecorator(context: CalendarFragment, stayoutDays: MutableList<Int>?, month: Int) :
        DayViewDecorator {
        val stayoutDrawable = context.resources.getDrawable(R.drawable.style_calendar_cycle)
        val stayoutDays = stayoutDays
        val month = month

        override fun shouldDecorate(day: CalendarDay?): Boolean { // 커스텀 여부 반환
            return stayoutDays?.contains(day?.day) == true && day?.month == month
        }

        override fun decorate(view: DayViewFacade?) { // 커스텀 설정
            view?.setBackgroundDrawable(stayoutDrawable)
        }
    }


}