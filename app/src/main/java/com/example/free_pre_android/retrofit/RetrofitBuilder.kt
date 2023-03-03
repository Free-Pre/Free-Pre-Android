package com.example.free_pre_android.retrofit

import com.example.free_pre_android.BuildConfig.BASE_URL
import com.example.free_pre_android.api.LoginService
import com.example.free_pre_android.api.PeriodService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    //사용할 API 인터페이스 선언
    val loginApi: LoginService
    val periodAPi:PeriodService

    val gson:Gson= GsonBuilder().setLenient().create()

    init{
        //API 서버 연결
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        loginApi=retrofit.create(LoginService::class.java)
        periodAPi=retrofit.create(PeriodService::class.java)
    }
}