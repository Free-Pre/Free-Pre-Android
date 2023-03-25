package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.CycleCheckResultDTO
import com.example.free_pre_android.data.PeriodAddDTO
import com.example.free_pre_android.data.PeriodAddResultDTO
import com.example.free_pre_android.databinding.ActivityRecentPeriodBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.util.*

class RecentPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRecentPeriodBinding
    var start_year: String? = ""
    var start_month: String? = ""
    var start_day: String? = ""
    var end_year: String? = ""
    var end_month: String? = ""
    var end_day: String? = ""
    val fragmentManager=supportFragmentManager
    var startFragment:RecentPeriodStartFragment?=null
    var endFragment:RecentPeriodEndFragment?=null
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecentPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //이메일 데이터 가져오기 - test용
        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        RetrofitBuilder.versionApi.cycleCheck(email).enqueue(object :Callback<CycleCheckResultDTO>{
            override fun onResponse(call: Call<CycleCheckResultDTO>, response: Response<CycleCheckResultDTO>) {
                if(response.isSuccessful){
                    Log.d("RECENT_PERIOD_CYCLE",response.body().toString())
                    if(response.body()?.result==true){//이미 free였던 적이 있음. FreeHome으로 넘기기
                        Log.d("RECENT_PERIOD","cycle: true")
                        val intent = Intent(this@RecentPeriodActivity, FreeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            override fun onFailure(call: Call<CycleCheckResultDTO>, t: Throwable) {
            }
        })

        //초기 화면
        initSetFragment()
        //start 버튼 누를 시
        viewBinding.startBtn.setOnClickListener {
            setRecentPeriodStartFragment()
        }
        //end 버튼 누를 시
        viewBinding.endBtn.setOnClickListener {
            setRecentPeriodEndFragment()
        }

        viewBinding.saveBtn.setOnClickListener {
            //월경일 입력 api 연결하기
            saveDate()
        }
    }
    fun initSetFragment(){
        startFragment= RecentPeriodStartFragment()
        fragmentManager.beginTransaction().replace(viewBinding.frameFragment.id, startFragment!!).commit()
        startBtn()
    }
    fun setRecentPeriodStartFragment() {
        if(startFragment==null){
            startFragment= RecentPeriodStartFragment()
            fragmentManager.beginTransaction().add(viewBinding.frameFragment.id, startFragment!!).commit()
        }
        if(startFragment!=null){
            fragmentManager.beginTransaction().show(startFragment!!).commit()
        }
        if(endFragment!=null){
            fragmentManager.beginTransaction().hide(endFragment!!).commit()
        }
        startBtn()
    }
    fun setRecentPeriodEndFragment() {
        if(endFragment==null){
            endFragment= RecentPeriodEndFragment()
            fragmentManager.beginTransaction().add(viewBinding.frameFragment.id, endFragment!!).commit()
        }
        if(endFragment!=null){
            fragmentManager.beginTransaction().show(endFragment!!).commit()
        }
        if(startFragment!=null){
            fragmentManager.beginTransaction().hide(startFragment!!).commit()
        }
        endBtn()
    }
    fun startBtn(){
        //startBtn 색 채우기-start 버튼
        viewBinding.startBtn.setBackgroundResource(R.drawable.style_clicked_period_border)
        viewBinding.startBtn.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //endBtn 색 없애기 - end 버튼
        viewBinding.endBtn.setBackgroundResource(R.drawable.style_unclicked_period_border)
        viewBinding.endBtn.setTextColor(Color.parseColor("#FDE3F4"))
    }
    fun endBtn(){
        //endBtn 색 채우기-end 버튼
        viewBinding.endBtn.setBackgroundResource(R.drawable.style_clicked_period_border)
        viewBinding.endBtn.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //startBtn 색 없애기 -start 버튼
        viewBinding.startBtn.setBackgroundResource(R.drawable.style_unclicked_period_border)
        viewBinding.startBtn.setTextColor(Color.parseColor("#FDE3F4"))
    }
    fun saveDate(){
        start_month=if(start_month?.trim()?.length==1)"0"+start_month?.trim()else start_month?.trim()
        start_day=if(start_day?.trim()?.length==1)"0"+start_day?.trim()else start_day?.trim()
        end_month=if(end_month?.trim()?.length==1)"0"+end_month?.trim()else end_month?.trim()
        end_day=if(end_day?.trim()?.length==1)"0"+end_day?.trim()else end_day?.trim()

        val start_date= "$start_year$start_month$start_day"
        val end_date= "$end_year$end_month$end_day"

        if(start_date.length!=8||end_date.length!=8){
            Log.e("RECENT_PERIOD","length error")
            Toast.makeText(this,"please enter a valid date",Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val sdf = SimpleDateFormat("yyyyMMdd")
            sdf.isLenient = false
            sdf.parse(start_date)
            sdf.parse(end_date)
        } catch (e: ParseException) {
            Log.e("RECENT_PERIOD","not valid date")
            e.printStackTrace()
            return
        }
        if(start_date.toInt()>end_date.toInt()){
            Log.e("RECENT_PERIOD","start date>end date")
            Toast.makeText(this,"start date can't be later than end date",Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("RECENT_PERIOD",start_date+" "+end_date)
        val dateInfo=PeriodAddDTO(email, "$start_year.$start_month.$start_day","$end_year.$end_month.$end_day")
        RetrofitBuilder.periodAPi.periodAddFirst(dateInfo).enqueue(object: Callback<PeriodAddResultDTO>{
            override fun onResponse(call: Call<PeriodAddResultDTO>, response: Response<PeriodAddResultDTO>) {
                Log.d("RECENT_PERIOD",response.body().toString())
                Log.d("RECENT_PERIOD",response.message())
                if(response.isSuccessful){
                    val intent=Intent(this@RecentPeriodActivity,FreeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Log.e("RECENT_PERIOD","response fail")
                }
            }
            override fun onFailure(call: Call<PeriodAddResultDTO>, t: Throwable) {
                Log.e("RECENT_PERIOD",t.message.toString())
            }
        })
    }
}
