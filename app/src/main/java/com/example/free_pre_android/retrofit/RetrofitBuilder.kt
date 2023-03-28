package com.example.free_pre_android.retrofit

import com.example.free_pre_android.BuildConfig.BASE_URL
import com.example.free_pre_android.api.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitBuilder {
    //사용할 API 인터페이스 선언
    val loginApi: LoginService
    val periodAPi:PeriodService
    val calendarApi: CalendarService         //캘린더
    val symptomApi : SymptomService
    val versionApi:VersionService
    val userApi : UserService
    val settingApi: SettingService

    val gson:Gson= GsonBuilder().setLenient().create()

    init{
        //API 서버 연결
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        loginApi=retrofit.create(LoginService::class.java)
        periodAPi=retrofit.create(PeriodService::class.java)
        calendarApi = retrofit.create(CalendarService::class.java)    //캘린더
        symptomApi = retrofit.create(SymptomService::class.java)     //증상
        versionApi=retrofit.create(VersionService::class.java)
        userApi = retrofit.create(UserService::class.java)           //유저관련련
        settingApi=retrofit.create(SettingService::class.java)
    }}