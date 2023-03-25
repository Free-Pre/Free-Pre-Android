package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.SharedPreferences
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.PeriodAddDTO
import com.example.free_pre_android.data.PeriodAddResultDTO
import com.example.free_pre_android.data.PeriodUpdateDTO
import com.example.free_pre_android.data.PeriodUpdateResultDTO
import com.example.free_pre_android.databinding.ActivityEditPeriodBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException

class EditPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEditPeriodBinding
    var start_year: String? = ""
    var start_month: String? = ""
    var start_day: String? = ""
    var end_year: String? = ""
    var end_month: String? = ""
    var end_day: String? = ""
    val fragmentManager=supportFragmentManager
    var startFragment:EditPeriodStartFragment?=null
    var endFragment:EditPeriodEndFragment?=null
    var email=""
    var period_id:Int=-1//-1이면 월경일 입력. 이외엔 월경일 편집
    var start_date:String="0000.00.00"
    var end_date:String="0000.00.00"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //이메일 데이터 가져오기 - test용
        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        //EdirPreiodList에서 넘어온 intent
        period_id=intent.getIntExtra("period_id",-1)
        Log.d("EDIT_PERIOD",period_id.toString())
        if(period_id==-1){//월경일 추가

        }
        else{//월경일 편집
            start_date=intent.getStringExtra("start_date").toString()
            end_date=intent.getStringExtra("end_date").toString()
        }
        //초기 화면
        initSetFragment()
        //start 버튼 누를 시
        viewBinding.btnStart.setOnClickListener {
            setEditPeriodStartFragment()
        }
        //end 버튼 누를 시
        viewBinding.btnEnd.setOnClickListener {
            setEditPeriodEndFragment()
        }

        viewBinding.btnSave.setOnClickListener {
            //월경일 입력 api 연결하기
            saveDate()
        }
    }
    fun initSetFragment(){
        startFragment= EditPeriodStartFragment()
        fragmentManager.beginTransaction().replace(viewBinding.frameFragment.id, startFragment!!).commit()
        startBtn()
    }
    fun setEditPeriodStartFragment() {
        if(startFragment==null){
            startFragment= EditPeriodStartFragment()
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
    fun setEditPeriodEndFragment() {
        if(endFragment==null){
            endFragment= EditPeriodEndFragment()
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
        viewBinding.btnStart.setBackgroundResource(R.drawable.style_clicked_period_border)
        viewBinding.btnStart.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //endBtn 색 없애기 - end 버튼
        viewBinding.btnEnd.setBackgroundResource(R.drawable.style_unclicked_period_border)
        viewBinding.btnEnd.setTextColor(Color.parseColor("#FDE3F4"))
    }
    fun endBtn(){
        //endBtn 색 채우기-end 버튼
        viewBinding.btnEnd.setBackgroundResource(R.drawable.style_clicked_period_border)
        viewBinding.btnEnd.setTextColor(Color.parseColor("#1A2A46"))   //dark

        //startBtn 색 없애기 -start 버튼
        viewBinding.btnStart.setBackgroundResource(R.drawable.style_unclicked_period_border)
        viewBinding.btnStart.setTextColor(Color.parseColor("#FDE3F4"))
    }
    fun saveDate(){
        start_month=if(start_month?.trim()?.length==1)"0"+start_month?.trim()else start_month?.trim()
        start_day=if(start_day?.trim()?.length==1)"0"+start_day?.trim()else start_day?.trim()
        end_month=if(end_month?.trim()?.length==1)"0"+end_month?.trim()else end_month?.trim()
        end_day=if(end_day?.trim()?.length==1)"0"+end_day?.trim()else end_day?.trim()

        val start_date= "$start_year$start_month$start_day"
        val end_date= "$end_year$end_month$end_day"
        Log.d("EDIT_PERIOD",start_date+" "+end_date)
        if(start_date.length!=8||end_date.length!=8){
            Log.e("EDIT_PERIOD","length error")
            Toast.makeText(this,"please enter a valid date", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val sdf = SimpleDateFormat("yyyyMMdd")
            sdf.isLenient = false
            sdf.parse(start_date)
            sdf.parse(end_date)
        } catch (e: ParseException) {
            Log.e("EDIT_PERIOD","not valid date")
            e.printStackTrace()
            return
        }
        if(start_date.toInt()>end_date.toInt()){
            Log.e("EDIT_PERIOD","start date>end date")
            Toast.makeText(this,"start date can't be later than end date", Toast.LENGTH_SHORT).show()
            return
        }
        //윌경일 입력
        if(period_id==-1) {
            val dateInfo=PeriodAddDTO(email, "$start_year.$start_month.$start_day","$end_year.$end_month.$end_day")
            RetrofitBuilder.periodAPi.periodAdd(dateInfo).enqueue(object: Callback<PeriodAddResultDTO>{
                override fun onResponse(call: Call<PeriodAddResultDTO>, response: Response<PeriodAddResultDTO>) {
                    Log.d("EDIT_PERIOD",response.body().toString())

                    finish()
                    /*if(response.isSuccessful){//response 오류 수정 필요
                        Log.d("EDIT_PERIOD","response success")
                        finish()
                    }
                    else{
                        Log.e("EDIT_PERIOD","response fail")
                    }*/
                }
                override fun onFailure(call: Call<PeriodAddResultDTO>, t: Throwable) {
                    Log.e("EDIT_PERIOD",t.message.toString())
                }
            })
        }
        //월경일 편집
        else{
            val dateInfo= PeriodUpdateDTO(email, "$start_year.$start_month.$start_day","$end_year.$end_month.$end_day")
            RetrofitBuilder.periodAPi.periodEdit(period_id,dateInfo).enqueue(object: Callback<PeriodUpdateResultDTO>{
                override fun onResponse(call: Call<PeriodUpdateResultDTO>, response: Response<PeriodUpdateResultDTO>) {
                    Log.d("EDIT_PERIOD_EDIT","period_id: $period_id")
                    Log.d("EDIT_PERIOD_EDIT",response.body().toString())
                    Log.d("EDIT_PERIOD_EDIT",response.message().toString())
                    Log.d("EDIT_PERIOD_EDIT",response.code().toString())
                    finish()
                    /*if(response.isSuccessful){//response 오류 수정 필요
                        Log.d("EDIT_PERIOD","response success")
                        finish()
                    }
                    else{
                        Log.e("EDIT_PERIOD","response fail")
                    }*/
                }
                override fun onFailure(call: Call<PeriodUpdateResultDTO>, t: Throwable) {
                    Log.e("EDIT_PERIOD_EDIT",t.message.toString())
                }
            })
        }

    }
}


