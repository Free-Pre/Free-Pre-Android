package com.example.free_pre_android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


class CalendarFragment : Fragment() {
    private lateinit var viewBinding: FragmentCalendarBinding
    private lateinit var calendarView: MaterialCalendarView
    var userEmail: String = ""
    var month: String = ""

    var selectDate: String = ""

    //시작, 끝 날짜
    var startDay: String = ""
    var endDay: String = ""

    var startPeriodYear: String = ""
    var startPeriodMonth: String = ""
    var startPeriodDate: String = ""

    var endPeriodYear: String = ""
    var endPeriodMonth: String = ""
    var endPeriodDate: String = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater)
        return viewBinding.root

        //CalendarCheck("${userEmail}","${month}")

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = viewBinding.calendarView

        //이메일 불러오기
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        userEmail = sharedPreferences.getString("emailKey", "there's no email")
            .toString()               //로그인 되어있는 유저의 이메일
        month = viewBinding.calendarView.currentDate.month.toString()                    //현재달


        selectDate = viewBinding.calendarView.selectedDate.toString()
        Log.d("TestEmail", "${selectDate}")    //null값 들어옴

        Log.d("TestEmail", "${userEmail}")    //잘 들어옴
        Log.d("TestEmail", "${month}")    //잘 들어옴

        //user_email = "example@test.com"
        //user_month ="202203"

        viewBindingRun()
        CustomCalendar()
        viewBindingView()
        CalendarCheck("${userEmail}", "${month}")       //api 연결


        // 요일 선택 시 내가 정의한 드로어블이 적용되도록 함
        calendarView.setOnRangeSelectedListener { widget, dates -> // 아래 로그를 통해 시작일, 종료일이 어떻게 찍히는지 확인하고 본인이 필요한 방식에 따라 바꿔 사용한다
        }

        // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
        //calendarView.addDecorators(TodayDecorator(this))


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun viewBindingView() {
        //화살표 색상
        viewBinding.calendarView.leftArrow.setTint(
            ContextCompat.getColor(
                requireActivity(),
                R.color.primary_light
            )
        )
        viewBinding.calendarView.rightArrow.setTint(
            ContextCompat.getColor(
                requireActivity(),
                R.color.primary_light
            )
        )

        calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        //오늘 날짜 표시
        val today = CalendarDay.today()
        calendarView.addDecorators(CurrentDayDecorator(activity, today))
        //viewBinding.calendarView.selectedDate = CalendarDay.today()     //오늘 날짜 표시


    }

    //클릭시 이동
    fun viewBindingRun() {
        viewBinding.run {
            button.setOnClickListener {
                startActivity(Intent(activity, SymptomActivity::class.java))
            }
            btnSetting.setOnClickListener {
                startActivity(Intent(activity, FreeSettingActivity::class.java))
            }
        }
    }

    fun CustomCalendar() {
        //달, 월 영어로 표시
        //calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        //calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

    }


    //레트로핏
    fun CalendarCheck(
        userEmail: String,
        month: String,
    ) {
        // API 호출
        RetrofitBuilder.calendarApi.calendarCheck(userEmail, month)
            .enqueue(object : Callback<CalendarCheckResultDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<CalendarCheckResultDTO>,
                    response: Response<CalendarCheckResultDTO>
                ) {
                    Log.d("calendar11", response.body().toString())
                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val result: CalendarCheckResultDTO? = response.body()
                        result?.let {
                            // result 객체를 이용하여 UI 업데이트 등의 작업 수행
                            Log.d("calendarTest", "연결성공")

                            //result의 start_date 부분
                            val firstResult = it.result.firstOrNull()          //result 리스트 가져옴
                            startDay = firstResult?.start_date.toString()      //시작 날짜
                            endDay = firstResult?.end_date.toString()          //끝 날짜
                            Log.d("calendarTest", startDay)
                            Log.d("calendarTest", endDay)

                            startPeriodYear = startDay.substring(0, 4)          //2023
                            startPeriodMonth = startDay.substring(5, 7)         //03
                            startPeriodDate = startDay.substring(8, 10)         //03

                            endPeriodYear = endDay.substring(0, 4)              //2023
                            endPeriodMonth = endDay.substring(5, 7)              //03
                            endPeriodDate = endDay.substring(8, 10)             //10
                            Log.d("calendarTest", "안녕 : ${endPeriodMonth.toInt()}")


                            // start_date와 end_date를 String 형태로 받아옴
                            val start_date = firstResult?.start_date.toString()
                            val end_date = firstResult?.end_date.toString()

                            // 날짜 포맷 지정
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                            // start_date와 end_date를 LocalDate 객체로 변환
                            val startDate = LocalDate.parse(start_date, formatter)
                            val endDate = LocalDate.parse(end_date, formatter)


                            // CalendarDay 객체 생성
                            val startDay = CalendarDay.from(startDate.year, startDate.monthValue, startDate.dayOfMonth)
                            Log.d("calendarTest", "안녕 : ${startDay}")
                            val endDay = CalendarDay.from(endDate.year, endDate.monthValue, endDate.dayOfMonth)

                            // start_date와 end_date 사이의 날짜를 모두 Decorator로 지정하여 핑크 배경으로 만듦
                            viewBinding.calendarView.addDecorator(RangeDayDecorator(context!!,startDay, endDay))


                            val clickedDrawable = ContextCompat.getDrawable(context!!, R.drawable.style_calendar_clicked)
                            val clickedDayDecorator = ClickedDayDecorator(clickedDrawable!!)
                            calendarView.addDecorator(clickedDayDecorator)

                            calendarView.setOnDateChangedListener { widget, date, selected ->
                                clickedDayDecorator.setClickedDay(date)
                                widget.invalidateDecorators()
                            }


                        }
                    } else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("calendarTest", "연결미스")
                    }
                }

                override fun onFailure(call: Call<CalendarCheckResultDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("calendarTest", t.message.toString())
                }
            })
    }

    //현재 날짜
    class CurrentDayDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
        private val drawable: Drawable?
        var myDay = currentDay
        override fun shouldDecorate(day: CalendarDay): Boolean {
            return day == myDay }
        override fun decorate(view: DayViewFacade) {
            view.setSelectionDrawable(drawable!!)
        }
        init {
            // You can set background for Decorator via drawable here
            drawable = ContextCompat.getDrawable(context!!, R.drawable.style_calendar_current)
        }
    }

    //선택한 날짜
    class ClickedDayDecorator(private val drawable: Drawable) : DayViewDecorator {
        private var clickedDay: CalendarDay? = null

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day == clickedDay
        }
        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(drawable)
        }
        fun setClickedDay(day: CalendarDay) {
            clickedDay = day
        }
    }

    class RangeDayDecorator(
        private val context: Context,
        private val startDate: CalendarDay,
        private val endDate: CalendarDay,
        //private val textColor: Int
    ) : DayViewDecorator {

        //val defaultDrawable = context.resources.getDrawable(R.drawable.style_calendar_cycle)
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.isInRange(startDate, endDate) ?: false
        }

        override fun decorate(view: DayViewFacade?) {
            //view?.addSpan(ForegroundColorSpan(textColor))       이건 왜 안되지..
            //view?.setBackgroundDrawable(defaultDrawable)
            view?.addSpan(object: ForegroundColorSpan(Color.parseColor("#1A2A46")){})
            view!!.setSelectionDrawable(ContextCompat.getDrawable(context, R.drawable.style_calendar_cycle)!!)
        }
    }


    /*
    @RequiresApi(Build.VERSION_CODES.N)
    class CycleDecorator(
        var startCycleDate: String,
        var endCycleDate: String
    ) : DayViewDecorator {

        @RequiresApi(Build.VERSION_CODES.N)
        private val Day = Calendar.getInstance()
        @RequiresApi(Build.VERSION_CODES.N)
        private val startCycleDay = Calendar.getInstance()
        @RequiresApi(Build.VERSION_CODES.N)
        private val endCycleDay = Calendar.getInstance()

        init {
            startCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startCycleDate)
            endCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endCycleDate)

        }

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            val dateInMillis1 = day?.date?.time
            val dayInMillis = day?.date ?: return false
            Log.d("calendarTest", "${dayInMillis}")
            val startCycleInMillis = startCycleDay.timeInMillis
            val endCycleInMillis = endCycleDay.timeInMillis
            return dayInMillis >= startCycleInMillis && dayInMillis <= endCycleInMillis
        }

        override fun decorate(view: DayViewFacade?) {
            view?.addSpan(object: StyleSpan(Typeface.BOLD){})
            view?.addSpan(object: RelativeSizeSpan(1.4f){})
            view?.addSpan(object: ForegroundColorSpan(Color.parseColor("#E80000")){})
        }
    }*/

    /*
    class CycleDecorator1(
        val startCycleDate: String,
        val endCycleDate: String
    ) : DayViewDecorator {

        private val Day = Calendar.getInstance()
        private val startCycleDay = Calendar.getInstance()
        private val endCycleDay = Calendar.getInstance()
        private val drawable = ContextCompat.getDrawable(MaterialCalendarView(context), R.drawable.calendar_selector)

        init {
            startCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startCycleDate)
            endCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endCycleDate)
        }

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            val dayInMillis = day?.Day?.time ?: return false
            val startCycleInMillis = startCycleDay.timeInMillis
            val endCycleInMillis = endCycleDay.timeInMillis
            return dayInMillis >= startCycleInMillis && dayInMillis <= endCycleInMillis
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(drawable)
        }
    } */


    /*
    @RequiresApi(Build.VERSION_CODES.N)
    class CycleDecorator(
        val startCycleDate: String,
        val endCycleDate: String,
        context: Context
    ) : DayViewDecorator {
        private val startCycleDay = Calendar.getInstance()
        private val endCycleDay = Calendar.getInstance()
        private val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.calendar_selector)

        init {
            startCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startCycleDate)
            endCycleDay.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endCycleDate)
        }
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return if (day != null) {
                day.date in startCycleDay.time..endCycleDay.time
            } else {
                false
            }
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(drawable!!)
        }
    }





    /*
    class MenstrualCycleDecorator(
        context: Context,
        private val startYear: Int,
        private val startMonth: Int,
        private val startDate: Int,
        private val endYear: Int,
        private val endMonth: Int,
        private val endDate: Int
    ) : DayViewDecorator {

        private val drawable: Drawable?

        init {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector)
        }


        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldDecorate(day: CalendarDay): Boolean {
            val day = Calendar.getInstance()              //현재 날짜와 현재 시간 저장
            val startCycleDay = Calendar.getInstance().apply {
                set(startYear, startMonth, startDate)
            }

            val endCycleDay = Calendar.getInstance().apply {
                set(endYear, endMonth, endDate)
            }

            // 주기 계산
            val cyclePeriod = 28 * 24 * 60 * 60 * 1000L // 28일
            val periodLength = endDate - startDate +1   //주기 기간


            // 주기에 해당하는 날짜 계산
            val dayInMillis = day.timeInMillis
            val startCycleInMillis = startCycleDay.timeInMillis
            val endCycleInMillis = endCycleDay.timeInMillis

            if (dayInMillis < startCycleInMillis || dayInMillis > endCycleInMillis) {
                // 주기 기간이 아니면 무시
                return false
            }

            val diff = dayInMillis - startCycleInMillis
            val mod = diff % cyclePeriod
            val isCycleDay = mod < periodLength

            return isCycleDay
        }

        override fun decorate(view: DayViewFacade) {
            view.addSpan(DotSpan(5f, drawable!!))
        }
    }*/

    /*class BoldDecorator(min:CalendarDay, max:CalendarDay): DayViewDecorator {
        val maxDay = max           //생리 시작 날짜
        val minDay = min           //생리 끝 날짜
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return (day?.month == maxDay.month && day.day <= maxDay.day)
                    || (day?.month == minDay.month && day.day >= minDay.day)
                    || (minDay.month < day?.month!! && day.month < maxDay.month)
        }
        override fun decorate(view: DayViewFacade?) {
            view?.addSpan(object: StyleSpan(Typeface.BOLD){})
            view?.addSpan(object: RelativeSizeSpan(1.4f){})
        }
    }*/


}

     */


    /*생리 주기 부분
    class BoldDecorator(
        minYear: Int,
        minMonth: Int,
        minDate: Int,
        maxYear: Int,
        maxMonth: Int,
        maxDate: Int
    ) : DayViewDecorator {
        val MinYear = minYear
        val MinMonth = minMonth
        val MinDate = minDate
        val MaxYear = maxYear
        val MaxMonth = maxMonth
        val MaxDate = maxDate

        override fun shouldDecorate(day: CalendarDay?): Boolean { //커스텀 여부 반환
            return (day?.month == MaxMonth && day.day <= MaxDate)
                    || (day?.month == MinMonth && day.day >= MinDate)
                    || (MinMonth < day?.month!! && day.month < MaxMonth)
            //Log.d("calendarTest", "잘 호출됨")
        }

        override fun decorate(view: DayViewFacade?) {            //커스텀 설정
            view?.addSpan(object : StyleSpan(Typeface.BOLD) {})
            view?.addSpan(object : RelativeSizeSpan(1.4f) {})
        }
    }*/
}



