package com.example.free_pre_android.retrofit

import com.example.free_pre_android.BuildConfig.BASE_URL
import com.example.free_pre_android.api.LoginService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    //사용할 API 인터페이스 선언
    val loginApi: LoginService

    val gson:Gson= GsonBuilder().setLenient().create()


    //private const val BASE_URL_NEW = "http://34.64.209.25:8080/"//보안 필요

    init{
        //API 서버 연결
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        loginApi=retrofit.create(LoginService::class.java)

    }
}