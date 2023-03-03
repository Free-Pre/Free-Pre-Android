package com.example.free_pre_android.api

import com.example.free_pre_android.data.PeriodAddDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PeriodService {
    //월경일 추가
    @POST("freepre/period/first")
    fun periodAdd(
        @Body period:PeriodAddDTO
    ): Call<Void>
}