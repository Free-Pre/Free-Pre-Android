package com.example.free_pre_android.api

import com.example.free_pre_android.data.SymptomCheckDTO
import com.example.free_pre_android.data.SymptomGetDTO
import com.example.free_pre_android.data.VersionChangeResultDTO
import retrofit2.Call
import retrofit2.http.*

interface SymptomService {
    //증상입력(서버에 증상 보내기)
    @POST("freepre/userSymptom")
    fun symptomCheck(
        @Body SymptomCheck: SymptomCheckDTO
    ): Call<Void>


    //증상 가져오기
    @GET("freepre/userSymptom/{email}/{date}")
    fun symptomGet(
        @Path("email")email:String,
        @Path("date")date:String
    ): Call<SymptomGetDTO>


    //증상 수정하기 - 다시
    @PATCH("freepre/usersymptom/edit/{email}/{date}")
    fun symptomModify(
        @Path ("email") email:String,
        @Path ("date") date:String,
        @Body symptom: SymptomCheckDTO,
    ): Call<VersionChangeResultDTO>

}