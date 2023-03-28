package com.example.free_pre_android.api

import com.example.free_pre_android.data.SettingInfoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SettingService {
    //setting 회원 정보 가져오기
    @GET("freepre/user/info/{userEmail}")
    fun settingUser(
        @Path("userEmail")userEmail:String
    ): Call<SettingInfoDTO>
}