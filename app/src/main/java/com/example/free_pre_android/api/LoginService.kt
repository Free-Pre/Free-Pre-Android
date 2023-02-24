package com.example.free_pre_android.api

import com.example.free_pre_android.data.emailCheckDTO
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    //이메일 확인
    @GET("freepre/user/userEmail")
    fun emailCheck(
        @Query("userEmail")user_email:String
    ): Call<emailCheckDTO>

}