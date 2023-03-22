package com.example.free_pre_android.api

import com.example.free_pre_android.data.PeriodAddDTO
import com.example.free_pre_android.data.PeriodAddResult
import com.example.free_pre_android.data.PeriodAddResultDTO
import com.example.free_pre_android.data.PeriodListResultDTO
import retrofit2.Call
import retrofit2.http.*

interface PeriodService {
    //최근 월경일 추가
    @POST("freepre/period/first")
    fun periodAddFirst(
        @Body period:PeriodAddDTO
    ): Call<Void>

    //월경일 추가
    @POST("freepre/period")
    fun periodAdd(
        @Body period:PeriodAddDTO
    ): Call<Void>

    //월경일 리스트 4개 가져오기
    @GET("freepre/period/{userEmail}")
    fun periodList(
        @Path("userEmail")userEmail:String
    ): Call<PeriodListResultDTO>
}