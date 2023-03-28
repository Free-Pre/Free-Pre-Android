package com.example.free_pre_android.api

import com.example.free_pre_android.data.EmailCheckResultDTO
import com.example.free_pre_android.data.SignUpDTO
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    //이메일 확인
    @GET("freepre/user/check/{userEmail}")
    fun emailCheck(
        @Path("userEmail")userEmail:String
    ): Call<EmailCheckResultDTO>

    //회원가입
    @POST("freepre/user")
    fun signUp(
        @Body signUp: SignUpDTO
    ):Call<Void>
}