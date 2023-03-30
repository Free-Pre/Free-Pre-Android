package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.free_pre_android.data.PeriodAddDTO
import com.example.free_pre_android.data.PeriodAddResultDTO
import com.example.free_pre_android.databinding.FragmentFreeHomeLeftStartBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class FreeHomeLeftStartFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeLeftStartBinding
    var email=""
    var startDateString:String?=""
    var endDate :String?=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeLeftStartBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //이메일 데이터 가져오기
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        //남은 일 수 불러오기
        val sharedTerm: SharedPreferences = requireActivity().getSharedPreferences("CycleTerm", Activity.MODE_PRIVATE)
        val term = sharedTerm.getInt("cycleTerm", 0)
        Log.d("PostPeriod","term은 : ${term.toString()}")

        val pattern = "yyyy.MM.dd" // 원하는 패턴 지정
        val simpleDateFormat = SimpleDateFormat(pattern)
        startDateString = simpleDateFormat.format(Date()) // 시작일을 문자열로 지정
        val startDate = simpleDateFormat.parse(startDateString) // 시작일을 Date 타입으로 변환
        Log.d("PostPeriod","시작 날짜는 : ${startDate.toString()}")

        val calendar = Calendar.getInstance() // Calendar 객체 생성
        calendar.time = startDate // Calendar 객체에 시작일 지정
        calendar.add(Calendar.DAY_OF_YEAR, term) // 시작일에서 기간만큼 더하기
        endDate = simpleDateFormat.format(calendar.time) // 포맷에 맞게 날짜를 문자열로 변환

        Log.d("PostPeriod", "예상 startDate: ${startDateString.toString()}, endDate 예상: $endDate") // 로그 출력

        //startDateString과 endDate가 날짜의 문자열이다. (전역변수에 선언되어 있음)



        //startbtn누르면
        viewBinding.btnStartPeriod.setOnClickListener {
            /*val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.top_over_start, FreeHomeOnMyPeriodFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            Log.d("TopOverStart", "월경 진행 날짜 시작")*/

            //최신 월경 날짜 보내기
            PostPeriod()

            //startActivity(Intent(context,FreeActivity::class.java))
        }

        //남은 일 수 불러오기
        val sharedDayDiff: SharedPreferences = requireActivity().getSharedPreferences("DayDiff", Activity.MODE_PRIVATE)
        viewBinding.textLeftStart.text = sharedDayDiff.getLong("dayDiff", 0L).toString()+" DAYS LEFT"

    }

    //월경이 시작된 날짜, 예정된 끝 날짜 db에 보내기
    fun PostPeriod() {
        val dateInfo= PeriodAddDTO(email, startDateString!!,endDate!!)        //startDateString은 String형, endDate는 String형
        // API 호출
        RetrofitBuilder.periodAPi.periodAdd(dateInfo)
            .enqueue(object : Callback<PeriodAddResultDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(call: Call<PeriodAddResultDTO>, response: Response<PeriodAddResultDTO>) {
                    Log.d("PostPeriod", response.body().toString())
                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val resultHomeInfo: PeriodAddResultDTO? = response.body()
                        resultHomeInfo?.let {
                            Log.d("PostPeriod", "연결성공")


                        }
                        startActivity(Intent(context,MainActivity::class.java))
                    }else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("PostPeriod", "연결미스")
                    }
                }

                override fun onFailure(call: Call<PeriodAddResultDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("PostPeriod", t.message.toString())
                }
            })
    }


}