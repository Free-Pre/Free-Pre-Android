package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_pre_android.adapter.PeriodListAdapter
import com.example.free_pre_android.data.PeriodListResult
import com.example.free_pre_android.data.PeriodListResultDTO
import com.example.free_pre_android.databinding.ActivityEditPeriodListBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPeriodListActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityEditPeriodListBinding
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditPeriodListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //이메일 데이터 가져오기 - test용
        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d("EDIT_PERIOD_LIST","email: $email")
        getList()
        viewBinding.btnAdd.setOnClickListener {
            startActivity(Intent(this@EditPeriodListActivity, EditPeriodActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getList()
    }
    fun getList(){
        RetrofitBuilder.periodAPi.periodList("flora7883@gmail.com").enqueue(object : Callback<PeriodListResultDTO> {
            override fun onResponse(call: Call<PeriodListResultDTO>, response: Response<PeriodListResultDTO>) {
                Log.d("EDIT_PERIOD_LIST",response.body().toString())
                Log.d("EDIT_PERIOD_LIST",response.code().toString())
                if(response.isSuccessful){
                    Log.d("EDIT_PERIOD_LIST","response success")
                    val periodList:MutableList<PeriodListResult> = mutableListOf()
                    for(info in response.body()?.result!!){
                        var period=PeriodListResult(
                            info.period_id,
                            info.email,
                            info.start_date,
                            info.end_date
                        )
                        periodList.add(period)
                    }
                    val adapter=PeriodListAdapter(this@EditPeriodListActivity)
                    adapter.periodList=periodList
                    viewBinding.recyclerPeriodList.adapter=adapter
                    viewBinding.recyclerPeriodList.layoutManager=LinearLayoutManager(this@EditPeriodListActivity)
                }
                else{
                    Log.e("EDIT_PERIOD_LIST","response fail")
                }
            }
            override fun onFailure(call: Call<PeriodListResultDTO>, t: Throwable) {
                Log.d("EDIT_PERIOD_LIST",t.message.toString())
            }
        })
    }
}