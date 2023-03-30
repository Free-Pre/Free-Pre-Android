package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Context
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
import com.example.free_pre_android.data.HomeInfoDTO
import com.example.free_pre_android.databinding.FragmentFreeHomeBinding
import com.example.free_pre_android.information.FemaleDiseaseActivity
import com.example.free_pre_android.information.KnowledgeOfMenstruationActivity
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class FreeHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeBinding
    var userEmail: String = ""

    //전역변수 선언
    var calendar = Calendar.getInstance()
    var nextPeriodStartDate:Date = calendar.time
    var nextPeriodEndDate:Date = calendar.time
    var term:Int =0
    var cycle:Int=0
    lateinit var mycontext: Context
    val FragmentLeftEdit = FreeHomeLeftEditFragment()
    val FragmentLeftStart = FreeHomeLeftStartFragment()
    val FragmentOverStart = FreeHomeOverStartFragment()
    val FragmentEnterDate = FreeHomeEnterDateFragment()
    val FragmentMyPeriod = FreeHomeOnMyPeriodFragment()
    val FragmentPridctToday = FreeHomePredictToday()      //예정 당일

    override fun onAttach(context: Context) {
        Log.d("FreeHomeFragment","onAttach")
        super.onAttach(context)
        mycontext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFreeHomeBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBindingRun()

        Log.d("FreeHomeFragment","CreatedView")

        //이메일 불러오기
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        userEmail = sharedPreferences.getString("emailKey", "there's no email").toString()

        GetHomeInfo(userEmail)
        //Get4Cycle(userEmail)


    }

    override fun onStart() {
        super.onStart()
    }


    fun viewBindingRun(){
        viewBinding.run {
            btnCamera.setOnClickListener {
                startActivity(Intent(activity ,CameraActivity::class.java))   //getActivity or context -> fragment에서 this 불가
            }
            btnKnowledge.setOnClickListener {
                startActivity(Intent(activity, KnowledgeOfMenstruationActivity::class.java))
            }
            btnProducts.setOnClickListener {
                startActivity(Intent(activity, ProductsActivity::class.java))
            }
            btnFemaleDisease.setOnClickListener {
                startActivity(Intent(activity, FemaleDiseaseActivity::class.java))
            }
            btnFaq.setOnClickListener {
                startActivity(Intent(activity,FreeFaqActivity::class.java))
            }
            btnSetting.setOnClickListener {
                startActivity(Intent(activity,FreeSettingActivity::class.java))
            }

        }
    }


    /*
    fun predictNextPeriod(startDates: List<String>, endDates: List<String>) :String{
        // 시작 날짜와 끝 날짜 리스트의 크기가 모두 4가 아니면 예외를 던진다.
        require(startDates.size == 4 && endDates.size == 4) { "Start and end dates lists must contain exactly 4 elements" }

        // 시작 날짜와 끝 날짜를 기간으로 변환하여 리스트에 저장한다.
        val periods = mutableListOf<Long>()
        for (i in 0 until 4) {
            val startDate = SimpleDateFormat("yyyy.MM.dd").parse(startDates[i])
            val endDate = SimpleDateFormat("yyyy.MM.dd").parse(endDates[i])
            val period = ((endDate.time - startDate.time) / (1000 * 60 * 60 * 24))
            periods.add(period)
        }

        // 평균 주기와 평균 기간을 계산한다.
        val averageCycle = (periods.sum() / 3).toInt() // 마지막 3개 기간의 평균 주기  - 28
        val averagePeriod = (periods.average()).toInt() // 마지막 4개 기간의 평균 기간 - 5

        // 다음 생리 시작 날짜를 계산한다.
        val lastEndDate = SimpleDateFormat("yyyy.MM.dd").parse(endDates.first())          //가장 첫번째 원소가 최근 데이터이다.
        val nextStartDate = Date(lastEndDate.time + (averageCycle * 24 * 60 * 60 * 1000))

        return SimpleDateFormat("yyyy.MM.dd").format(nextStartDate)

    }*/



    //cycle,term,최근 월경날짜
    fun GetHomeInfo(
        userEmail: String
    ) {
        // API 호출
        RetrofitBuilder.homeApi.homeInfo(userEmail)
            .enqueue(object : Callback<HomeInfoDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(call: Call<HomeInfoDTO>, response: Response<HomeInfoDTO>) {
                    Log.d("GetHomeInfo", response.body().toString())
                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val resultHomeInfo: HomeInfoDTO? = response.body()
                        resultHomeInfo?.let {
                            Log.d("GetHomeInfo", "연결성공")

                            //term 값 저장
                            term = resultHomeInfo.result.term
                            setSharedTerm("CycleTerm", "cycleTerm", term)

                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            Log.d("GetHomeInfo", "dateFormat: ${dateFormat}")

                            val startDate = dateFormat.parse(resultHomeInfo.result.start_date)
                            Log.d("GetHomeInfo", "startDate: ${startDate}")

                            val endDate = dateFormat.parse(resultHomeInfo.result.end_date)
                            Log.d("GetHomeInfo","endDate: ${endDate}")

                            //Date 객체
                            //val calendar = Calendar.getInstance()

                            calendar.time = startDate
                            calendar.add(Calendar.DAY_OF_MONTH, resultHomeInfo.result.cycle)

                            //다음 월경 예정일
                            nextPeriodStartDate = calendar.time
                            Log.d("GetHomeInfo", "nextPeriodDate: ${nextPeriodStartDate}")



                            //현재 날짜
                            val currentDate = Date() // 현재 날짜와 시간을 가지고 있는 Date 객체
                            Log.d("GetHomeInfo", "currentDate: ${currentDate}")

                            //다음 예정일까지 남은 일수
                            //계산하기 위해서는 Date 객체의 time 속성을 이용
                            val timeDiff = nextPeriodStartDate.time - currentDate.time    //두 날짜 사이의 시간 차이 (예정일-현재날짜)
                            val daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) // 시간 차이를 일 수로 변환
                            setSharedDayDiff("DayDiff", "dayDiff", daysDiff)     //남은 일 수 저장
                            Log.d("GetHomeInfo", "남은 일 수: $daysDiff")

                            //오버 날짜 계산
                            val overTimeDiff = currentDate.time - nextPeriodStartDate.time     //현재 날짜 - 예정일
                            val overDaysDiff = TimeUnit.DAYS.convert(overTimeDiff, TimeUnit.MILLISECONDS) // 오버일 수로 변환

                            setSharedOverDiff("OverDiff", "overDiff", overDaysDiff)      //오버일 수 저장
                            Log.d("GetHomeInfo", "오버 일 수: $overDaysDiff")
//
                            setSharedStartDate("SharedStartDate","sharedStartDate",resultHomeInfo.result.start_date)
                            setSharedEndDate("SharedEndDate","sharedEndDate",resultHomeInfo.result.end_date)

                            if(!isAdded)return
                            val transaction = childFragmentManager.beginTransaction()

                            /*
                            if (daysDiff > 7 && daysDiff <= 14) {
                                //자식 프래그먼트 이동
                                //초기 Left-Edit
                                val transaction = childFragmentManager.beginTransaction()
                                transaction.replace(R.id.free_home_top_frame, FreeHomeLeftEditFragment())
                                transaction.addToBackStack(null)
                                transaction.commit()
                                Log.d("GetHomeInfo", "LeftEdit 호출됨")


                            } else if (daysDiff >= 0 && daysDiff < 7) {
                                    //Left-Start,0일포함 - 메시지를 다르게 주자.
                                    val transaction = childFragmentManager.beginTransaction()
                                    transaction.replace(R.id.free_home_top_frame, FreeHomeLeftStartFragment())
                                    transaction.addToBackStack(null)
                                    transaction.commit()
                                    Log.d("GetHomeInfo", "LeftStart 호출됨")


                            } else if (overDaysDiff > 0 && overDaysDiff <= 5) {   //예정일 오버할 때
                                val transaction = childFragmentManager.beginTransaction()
                                transaction.replace(R.id.free_home_top_frame, FreeHomeOverStartFragment())
                                transaction.addToBackStack(null)
                                transaction.commit()
                                Log.d("GetHomeInfo", "OverStart 호출됨")

                            }else{
                                val transaction = childFragmentManager.beginTransaction()
                                transaction.replace(R.id.free_home_top_frame, FreeHomeEnterDateFragment())
                                transaction.addToBackStack(null)
                                transaction.commit()
                                Log.d("GetHomeInfo", "EnterDate 호출됨")
                            }*/


                            if(currentDate > endDate){
                                if (daysDiff > 7 && daysDiff <= 14) {
                                    //자식 프래그먼트 이동
                                    //초기 Left-Edit
                                    val transaction = childFragmentManager.beginTransaction()
                                    transaction.replace(R.id.free_home_top_frame, FreeHomeLeftEditFragment())
                                    transaction.addToBackStack(null)
                                    transaction.commit()
                                    Log.d("GetHomeInfo", "LeftEdit 호출됨")


                                } else if (daysDiff >= 0 && daysDiff < 7) {
                                    //Left-Start,0일포함 - 메시지를 다르게 주자.
                                    val transaction = childFragmentManager.beginTransaction()
                                    transaction.replace(R.id.free_home_top_frame, FreeHomeLeftStartFragment())
                                    transaction.addToBackStack(null)
                                    transaction.commit()
                                    Log.d("GetHomeInfo", "LeftStart 호출됨")


                                } else if (overDaysDiff > 0 && overDaysDiff <= 5) {   //예정일 오버할 때
                                    val transaction = childFragmentManager.beginTransaction()
                                    transaction.replace(R.id.free_home_top_frame, FreeHomeOverStartFragment())
                                    transaction.addToBackStack(null)
                                    transaction.commit()
                                    Log.d("GetHomeInfo", "OverStart 호출됨")

                                }else{
                                    val transaction = childFragmentManager.beginTransaction()
                                    transaction.replace(R.id.free_home_top_frame, FreeHomeEnterDateFragment())
                                    transaction.addToBackStack(null)
                                    transaction.commit()
                                    Log.d("GetHomeInfo", "EnterDate 호출됨")
                                }
                            }else{//2
                                val transaction = childFragmentManager.beginTransaction()
                                transaction.replace(R.id.free_home_top_frame, FreeHomeOnMyPeriodFragment())
                                transaction.addToBackStack(null)
                                transaction.commit()
                                Log.d("GetHomeInfo", "OnMyPeriod 호출됨")
                            }




                        }
                    }else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("GetHomeInfo", "연결미스")
                    }
                }

                override fun onFailure(call: Call<HomeInfoDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("GetHomeInfo", t.message.toString())
                }
            })
    }

    //sharedPreference
    public fun setSharedDayDiff(name: String, key: String, data: Long) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(key, data)
        editor.apply()

        Log.d(ContentValues.TAG,"setSharedDayDiff: $data")   //값 잘 들어감

    }

    public fun getSharedDayDiff(name: String, key: String) {
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var sharedDayDiff: String? = sharedPreferences.getString(key,"there's no email")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"getSharedDayDiff: $sharedDayDiff")
    }

    //오버일수 저장
    public fun setSharedOverDiff(name: String, key: String, data: Long) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(key, data)
        editor.apply()

        Log.d(ContentValues.TAG,"SetEmail: $data")   //값 잘 들어감

    }

    public fun getSharedOverDiff(name: String, key: String) {
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var sharedOverDiff: String? = sharedPreferences.getString(key,"there's no email")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"GetEmail: $sharedOverDiff")
    }

    //월경 term
    public fun setSharedTerm(name: String, key: String, data: Int) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(key, data)
        editor.apply()

        Log.d(ContentValues.TAG,"setTerm: $data")   //값 잘 들어감

    }

    public fun getSharedTerm(name: String, key: String) {
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var sharedOverDiff: String? = sharedPreferences.getString(key,"there's no Term")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"GetEmail: $sharedOverDiff")
    }

    //월경 최근 시작 날짜
    public fun setSharedStartDate(name: String, key: String, data: String) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.apply()

        Log.d(ContentValues.TAG,"setSharedStartDate: $data")   //값 잘 들어감

    }

    public fun getSharedStartDate(name: String, key: String) {
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var sharedStartDate: String? = sharedPreferences.getString(key,"there's no sharedStartDate")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"sharedStartDate: $sharedStartDate")
    }

    //월경 term
    public fun setSharedEndDate(name: String, key: String, data: String) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.apply()

        Log.d(ContentValues.TAG,"setSharedEndDate: $data")   //값 잘 들어감

    }

    public fun getSharedEndDate(name: String, key: String) {
        var sharedPreferences: SharedPreferences = mycontext.getSharedPreferences(name, Activity.MODE_PRIVATE)
        var sharedStartDate: String? = sharedPreferences.getString(key,"there's no sharedStartDate")   //키에 상응하는 데이터가 없다면 두번째 파라미터에 적힌 디폴트 값을 반환한다.
        Log.d(ContentValues.TAG,"sharedEndDate: $sharedStartDate")
    }

}

    /*
    //최근 월경 정보 4개 가져오기
    fun Get4Cycle(
        userEmail: String
    ) {
        // API 호출
        RetrofitBuilder.periodAPi.periodList(userEmail)
            .enqueue(object : Callback<PeriodListResultDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(call: Call<PeriodListResultDTO>, response: Response<PeriodListResultDTO>
                ) {
                    Log.d("Get4Cycle", response.body().toString())

                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val result4Cycle: PeriodListResultDTO? = response.body()
                        result4Cycle?.let {
                            Log.d("Get4Cycle", "연결성공")

                            /*
                            //4개의 정보로 다음 월경일 예측
                            val startDates: MutableList<String> = mutableListOf("", "", "", "")
                            val endDates: MutableList<String> = mutableListOf("", "", "", "")

                            //리스트를 최근 날짜 순서대로 정렬
                            val sortedPeriods = it.result.sortedByDescending { it.start_date }

                            //startDates 배열에 start_date 4개를 담는다.
                            sortedPeriods.take(4).forEachIndexed{index,periodListResult->
                                startDates[index] = periodListResult.start_date
                            }
                            //endDates 배열에 end_date 4개를 담는다.
                            sortedPeriods.take(4).forEachIndexed { index, periodListResult ->
                                endDates[index] = periodListResult.end_date
                            }

                            /* startDates와 endDates 배열에 최근 월경일부터 4개씩 담기(for문으로 담는 형태-위에랑 같음)
                            for (i in 0 until 4) {
                                if (i < sortedPeriods.size) {
                                    startDates[i] = sortedPeriods[i].start_date
                                    endDates[i] = sortedPeriods[i].end_date
                                }
                            }*/

                            // 배열의 첫 번째 원소에는 리스트의 첫 번째 원소인 최근 날짜가 들어가도록 처리한다.
                            if (sortedPeriods.isNotEmpty()) {
                                startDates[0] = sortedPeriods[0].start_date
                                endDates[0] = sortedPeriods[0].end_date
                            }

                            Log.d("Get4Cycle","4개의 시작날 : ${startDates}")
                            Log.d("Get4Cycle","4개의 끝날 : ${endDates}")*/

                            /*sortedBy를 사용하여 오름차순으로 정렬 후 마지막 인덱스 부터 역순으로 채워넣기
                            val sortedPeriods = it.result.sortedBy { it.start_date }
                            val startDates: MutableList<String> = mutableListOf("", "", "", "")
                            val endDates: MutableList<String> = mutableListOf("", "", "", "")

                            for (i in sortedPeriods.indices.reversed().take(4)) {
                                val period = sortedPeriods[i]
                                val index = sortedPeriods.lastIndex - i
                                startDates[index] = period.start_date
                                endDates[index] = period.end_date
                            }*/

                            //predictNextPeriod(startDates,endDates)  //최근 월경일 4개로 평균 주기, 기간 계산
                            //Log.d("PredicDate",predictDate)


                        }
                    } else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("Get4Cycle", "연결미스")
                    }
                }

                override fun onFailure(call: Call<PeriodListResultDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("Get4Cycle", t.message.toString())
                }
            })*/




