package com.example.free_pre_android

import android.app.Activity
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
import com.example.free_pre_android.data.PeriodListResultDTO
import com.example.free_pre_android.databinding.FragmentFreeHomeBinding
import com.example.free_pre_android.information.FemaleDiseaseActivity
import com.example.free_pre_android.information.KnowledgeOfMenstruationActivity
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class FreeHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreeHomeBinding
    var userEmail: String = ""
    /*부분 fragment viewBinding
    private var childLeftEditBinding: FragmentFreeHomeLeftEditBinding?=null     //left-edit
    private var childLeftStartBinding: FragmentFreeHomeLeftStartBinding?=null   //left-start
    private var childOverStartBinding: FragmentFreeHomeOverStartBinding?=null   //over-start
    private var childEnterDateBinding: FragmentFreeHomeEnterDateBinding?=null   //enter-date
    private var childOnMyPeriodBinding: FragmentOnMyPeriodBinding?=null          //1st-edit*/

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

        val fragmentManager = childFragmentManager
        //childFragment 객체 생성
        val FragmentLeftEdit = FreeHomeLeftEditFragment()
        val FragmentLeftStart = FreeHomeLeftStartFragment()
        val FragmentOverStart = FreeHomeOverStartFragment()
        val FragmentEnterDate = FreeHomeEnterDateFragment()
        val FragmentMyPeriod = FreeHomeOnMyPeriodFragment()

        /*childFragment HashMap에 저장 - 키: 조건 값, 값: fragment객체
        val fragmentMap = hashMapOf(
            "fragmentLeftEdit" to FragmentLeftEdit,
            "fragmentLeftStart" to FragmentLeftStart,
            "fragmentOverStart" to FragmentOverStart,
            "fragmentEnterDate" to FragmentEnterDate,
            "fragmentMyPeriod" to FragmentMyPeriod,
        )*/

        /*val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            val condition = sharedPref.getString("childFragment", "fragmentLeftEdit")  //fragmentLeftEdit는 "childFragment"으로 저장된 키값이 없을 경우 리턴되는 디폴트값
            Log.d("hihihiTest",condition.toString())
            val selectedFragment = when (condition) {
                "fragmentLeftEdit" -> FragmentLeftEdit
                "fragmentLeftStart" -> FragmentLeftStart
                "fragmentOverStart" -> FragmentOverStart
                "fragmentEnterDate" -> FragmentEnterDate
                "fragmentMyPeriod" -> FragmentMyPeriod
                else -> FragmentLeftEdit // 기본값
            }

        }*/

        //자식 프래그먼트 이동
        //초기 Left-Start
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(viewBinding?.freeHomeTopFrame?.id ?: 0, FragmentLeftEdit)
        transaction.commit()

        //이메일 불러오기
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Email", Activity.MODE_PRIVATE)
        userEmail = sharedPreferences.getString("emailKey", "there's no email").toString()

        GetHomeInfo(userEmail)
        Get4Cycle(userEmail)


    }

    override fun onDestroyView() {
        super.onDestroyView()

        // View Binding 사용 후에는 해당 View에 대한 참조를 해제해주는 것이 좋습니다.

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
            /*
            btnEditPeriod.setOnClickListener {
                startActivity(Intent(activity,EditPeriodListActivity::class.java))
            }*/
        }
    }


    fun predictNextPeriod(startDates: List<String>, endDates: List<String>): String {
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
        val averageCycle = (periods.sum() / 3).toInt() // 마지막 3개 기간의 평균 주기
        val averagePeriod = (periods.average()).toInt() // 마지막 4개 기간의 평균 기간

        // 다음 생리 시작 날짜를 계산한다.
        val lastEndDate = SimpleDateFormat("yyyy.MM.dd").parse(endDates.last())
        val nextStartDate = Date(lastEndDate.time + (averageCycle * 24 * 60 * 60 * 1000))

        return SimpleDateFormat("yyyy.MM.dd").format(nextStartDate)
    }


    //cycle,term,최근 월경날짜
    fun GetHomeInfo(
        userEmail: String
    ) {
        // API 호출
        RetrofitBuilder.homeApi.homeInfo(userEmail)
            .enqueue(object : Callback<HomeInfoDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(call: Call<HomeInfoDTO>, response: Response<HomeInfoDTO>
                ) {
                    Log.d("GetHomeInfo", response.body().toString())

                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val resultHomeInfo: HomeInfoDTO? = response.body()
                        resultHomeInfo?.let {
                            Log.d("GetHomeInfo", "연결성공")
                            //val resultSymptom:SymptomGetDTO = result



                        }
                    } else {
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

                            //4개의 정보로 다음 월경일 예측
                            val startDates: MutableList<String> = mutableListOf("", "", "", "")
                            val endDates: MutableList<String> = mutableListOf("", "", "", "")

                            //startDates 배열에 start_date 4개를 담는다.
                            it.result.take(4).forEachIndexed{index,periodListResult->
                                startDates[index] = periodListResult.start_date
                            }
                            //endDates 배열에 end_date 4개를 담는다.
                            it.result.take(4).forEachIndexed { index, periodListResult ->
                                endDates[index] = periodListResult.end_date
                            }
                            Log.d("Get4Cycle","4개의 시작날 : ${startDates}")
                            Log.d("Get4Cycle","4개의 끝날 : ${endDates}")

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
            })
    }


}
