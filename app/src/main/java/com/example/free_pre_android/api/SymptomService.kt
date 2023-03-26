package com.example.free_pre_android.api

import com.example.free_pre_android.data.SymptomCheckDTO
import com.example.free_pre_android.data.SymptomGetDTO
import com.example.free_pre_android.data.SymptomModifyDTO
import retrofit2.Call
import retrofit2.http.*

interface SymptomService {
    //증상입력(서버에 증상 보내기)-SymptomActivity
    @POST("freepre/userSymptom")
    fun symptomCheck(
        @Body SymptomCheck: SymptomCheckDTO
    ): Call<Void>


    //증상 가져오기 - CalendarFragment
    @GET("freepre/userSymptom/{email}/{date}")
    fun symptomGet(
        @Path("email")email:String,
        @Path("date")date:String
    ): Call<SymptomGetDTO>


    //증상 수정하기 - SymptomActivity
    @PATCH("freepre/userSymptom/edit/{email}/{date}")
    fun symptomModify(
        @Path ("email")email: String,
        @Path ("date")date:String,
        @Body SymptomModify: SymptomModifyDTO,
    ): Call<SymptomModifyDTO>

}