package com.example.free_pre_android.api

import com.example.free_pre_android.data.HomeInfoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    //cycle, term, 최근 월경 시작 날짜 가져오기
    @GET("freepre/period/home/{userEmail}")
    fun homeInfo(
        @Path("userEmail")userEmail:String,
    ): Call<HomeInfoDTO>
}