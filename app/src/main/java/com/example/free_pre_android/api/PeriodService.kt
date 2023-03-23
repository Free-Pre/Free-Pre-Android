package com.example.free_pre_android.api

import androidx.core.content.PermissionChecker.PermissionResult
import com.example.free_pre_android.data.*
import retrofit2.Call
import retrofit2.http.*

interface PeriodService {
    //최근 월경일 추가
    @POST("freepre/period/first")
    fun periodAddFirst(
        @Body period:PeriodAddDTO
    ): Call<PeriodAddResultDTO>

    //월경일 추가
    @POST("freepre/period")
    fun periodAdd(
        @Body period:PeriodAddDTO
    ): Call<PeriodAddResultDTO>

    //월경일 편집
    @PATCH("freepre/period/edit/{periodId}")
    fun periodEdit(
        @Path("periodId") periodId:Int,
        @Body period:PeriodUpdateDTO,
    ):Call<PeriodUpdateResultDTO>

    //월경일 리스트 4개 가져오기
    @GET("freepre/period/{userEmail}")
    fun periodList(
        @Path("userEmail")userEmail:String
    ): Call<PeriodListResultDTO>
}