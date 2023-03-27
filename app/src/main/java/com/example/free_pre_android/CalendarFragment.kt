package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.free_pre_android.data.CalendarCheckResultDTO
import com.example.free_pre_android.data.SymptomGetDTO
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

    //시작, 끝 날짜
    var startDay: String = ""
    var endDay: String = ""

    var startPeriodYear: String = ""
    var startPeriodMonth: String = ""
    var startPeriodDate: String = ""

    var endPeriodYear: String = ""
    var endPeriodMonth: String = ""
    var endPeriodDate: String = ""


    lateinit var sharedPreferences: SharedPreferences     //sharedPreference




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = viewBinding.calendarView

        //이메일 불러오기
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        userEmail = sharedPreferences.getString("emailKey", "there's no email").toString()               //로그인 되어있는 유저의 이메일
        month = viewBinding.calendarView.currentDate.month.toString()                    //현재달


        viewBindingRun()
        viewBindingView()
        CalendarCheck("${userEmail}", "${month}")       //api 연결



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
            btnAddSymptom.setOnClickListener {
                val selectedDate = calendarView.selectedDate
                //Log.d("CalendarView","${selectedDate}")               //예시- CalendarDay{2023-3-22}
                if(selectedDate == null){
                    Toast.makeText(requireContext(), "Please select a date",Toast.LENGTH_SHORT).show()
                }else{
                    startActivity(Intent(activity, SymptomActivity::class.java))
                }

            }
            btnSetting.setOnClickListener {
                startActivity(Intent(activity, FreeSettingActivity::class.java))

            }

        }
    }

    private fun setSharedData(context: Context, key: String, data: org.threeten.bp.LocalDate?) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences =requireContext().getSharedPreferences("selected_date", Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, data.toString())
        editor.apply()

        Log.d(ContentValues.TAG,"SetClickDate: $data")   //값 잘 들어감
    }

    private fun getSharedData(context: Context, key: String, defaultValue: String) {
        var sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("selected_date", Activity.MODE_PRIVATE)
        var getSelectedDate: String? = sharedPreferences.getString(key,"No date selected.")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"getClickDate: $getSelectedDate")
    }

    //캘린더 api 연결
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
                            val endDay = CalendarDay.from(endDate.year, endDate.monthValue, endDate.dayOfMonth)

                            // 월경기간 데코-start_date와 end_date 사이의 날짜를 모두 Decorator로 지정하여 핑크 배경으로 만듦
                            viewBinding.calendarView.addDecorator(RangeDayDecorator(context!!,startDay, endDay))


                            //클릭한 날짜 데코
                            val clickedDrawable = ContextCompat.getDrawable(context!!, R.drawable.style_calendar_clicked)
                            val clickedDayDecorator = ClickedDayDecorator(clickedDrawable!!)
                            calendarView.addDecorator(clickedDayDecorator)

                            //날짜를 클릭하지 않았을 때 Symptom 위의 날짜는 현재 날짜로 한다. (디폴트)
                            val calendar = Calendar.getInstance()

                            //현재 달 한국어로 출력됨
                            //val TodayMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
                            //현재 달 숫자로 출력됨
                            //val TodayMonth = CalendarDay.today().month.toString()

                            //현재 달 영어로 출력됨 - March
                            val TodayMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
                            Log.d("hello", TodayMonth) // March

                            //날짜를 클릭하지 않았을 때는 현재 날짜가 보인다.
                            viewBinding.textSymptomSelectDate.text = "${TodayMonth} ${CalendarDay.today().day}"
                            Log.d("hello","noSelectClick: ${viewBinding.textSymptomSelectDate.text}")

                            //날짜 클릭 했을 때
                            calendarView.setOnDateChangedListener { widget, date, selected ->
                                val selectedDate = date?.date                     //선택된 날짜
                                //Log.d("hello","${selectedDate}")
                                clickedDayDecorator.setClickedDay(date)          //선택된 날짜 데코
                                widget.invalidateDecorators()

                                // 사용자가 선택한 날짜 저장 - sharedPreference
                                setSharedData(requireContext(), "selectDate",selectedDate) // 사용자가 선택한 날짜를 저장합니다.
                                Log.d("hello","setSharedData: ${selectedDate}")

                                // 사용자가 선택한 날짜 가져오기- sharedPreference
                                val getDate = getSharedData(requireContext(), "selectDate","")
                                //Log.d("hello","getSharedData: ${getDate}")

                                val selectedYear = selectedDate!!.year
                                val selectedMonth = selectedDate!!.month
                                val selectedDay = selectedDate!!.dayOfMonth

                                //날짜를 선택했으니 증상 위의 날짜 부분은 현재 날짜를 띄워준다.
                                viewBinding.textSymptomSelectDate.text = "${selectedMonth} ${selectedDay}"

                                //해당 날짜의 증상 가져오기
                                //email = 사용자의 이메일
                                //date = 선택한 날짜
                                GetSymptoms(userEmail,selectedDate.toString())
                            }
                            //선택한 날짜가 없다면 오늘 날짜의 증상들 보여주기
                            if(viewBinding.calendarView.selectedDate == null){
                                GetSymptoms(userEmail,CalendarDay.today().date.toString())  //오늘 날짜 증상들
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

    //해당 날짜에 증상 가져오기
    fun GetSymptoms(
        email: String,
        date: String,
    ) {
        // API 호출
        RetrofitBuilder.symptomApi.symptomGet(email, date)
            .enqueue(object : Callback<SymptomGetDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<SymptomGetDTO>,
                    response: Response<SymptomGetDTO>
                ) {
                    Log.d("GetSymptoms", response.body().toString())

                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val resultSymptom: SymptomGetDTO? = response.body()
                        resultSymptom?.let {
                            Log.d("GetSymptoms", "연결성공")
                            //val resultSymptom:SymptomGetDTO = result

                            if(resultSymptom.result == null){
                                viewBinding.textViewContents.text = "There are no recorded symptoms.\n" + "Enter your symptoms!"
                            }else{
                                val symptomList = mutableListOf<String>()
                                if(resultSymptom.result.vomit) symptomList.add("vomit")
                                if(resultSymptom.result.headache) symptomList.add("headache")
                                if(resultSymptom.result.backache) symptomList.add("backache")
                                if(resultSymptom.result.constipation) symptomList.add("constipation")
                                if(resultSymptom.result.giddiness) symptomList.add("giddiness")
                                if(resultSymptom.result.tiredness) symptomList.add("tiredness")
                                if(resultSymptom.result.fainting) symptomList.add("fainting")
                                if(resultSymptom.result.sensitivity) symptomList.add("sensitivity")
                                if(resultSymptom.result.acne) symptomList.add("acne")
                                if(resultSymptom.result.muscular_pain) symptomList.add("muscular_pain")
                                viewBinding.textViewContents.text = symptomList.joinToString(", ")

                            }

                        }
                    } else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("GetSymptoms", "연결미스")
                    }
                }

                override fun onFailure(call: Call<SymptomGetDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("GetSymptoms", t.message.toString())
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



}



