package com.example.free_pre_android.api

import com.example.free_pre_android.data.emailCheckResultDTO
import com.example.free_pre_android.data.preSignUpDTO
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    //이메일 확인
    @GET("freepre/user/userEmail")
    fun emailCheck(
        @Query("userEmail")user_email:String
    ): Call<emailCheckResultDTO>

    //pre 회원가입
    @POST("freepre/user")
    fun preSignUp(
        @Body preSignUp:preSignUpDTO
    ):Call<Void>
}