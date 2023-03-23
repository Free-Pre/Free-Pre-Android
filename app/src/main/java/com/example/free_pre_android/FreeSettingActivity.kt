package com.example.free_pre_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.free_pre_android.data.VersionChangeDTO
import com.example.free_pre_android.data.VersionChangeResultDTO
import com.example.free_pre_android.databinding.ActivityEditPeriodBinding
import com.example.free_pre_android.databinding.ActivityFreeSettingBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreeSettingActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityFreeSettingBinding
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityFreeSettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email= sharedPreferences.getString("emailKey","there's no email").toString()
        Log.d(ContentValues.TAG,"NickNameGetEmail: $email")

        viewBinding.btnFreeToPre.setOnClickListener {
            val datainfo= VersionChangeDTO(true)
            RetrofitBuilder.versionApi.versionChange(email,datainfo).enqueue(object:
                Callback<VersionChangeResultDTO> {
                override fun onResponse(call: Call<VersionChangeResultDTO>, response: Response<VersionChangeResultDTO>) {
                    Log.d("FREE_TO_PRE",response.code().toString())
                    Log.d("FREE_TO_PRE",response.body().toString())
                    Log.d("FREE_TO_PRE",response.message().toString())
                    val intent = Intent(this@FreeSettingActivity, PreActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<VersionChangeResultDTO>, t: Throwable) {
                    Log.e("FREE_TO_PRE",t.message.toString())
                }

            })
        }
    }
}