package com.example.free_pre_android.api

import com.example.free_pre_android.data.SymptomCheckDTO
import com.example.free_pre_android.data.SymptomGetDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SymptomService {
    //증상입력(서버에 증상 보내기)
    @POST("freepre/userSymptom")
    fun symptomCheck(
        @Body SymptomCheck: SymptomCheckDTO
    ): Call<Void>


    //증상 가져오기
    @GET("freepre/userSymptom/{userEmail}/{date}")
    fun symptomGet(
        @Query("email")email:String,
        @Query("date")date:String
    ): Call<SymptomGetDTO>
}