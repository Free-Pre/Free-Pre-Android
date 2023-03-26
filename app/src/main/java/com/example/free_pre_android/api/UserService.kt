package com.example.free_pre_android.api

import com.example.free_pre_android.data.NickNameChangeDTO
import com.example.free_pre_android.data.UserCycleDTO
import com.example.free_pre_android.data.UserInfoDTO
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    //닉네임 편집
   @PATCH("freepre/user/nickname/{userEmail}")
    fun editNickname(
        @Path("userEmail")userEmail: String,
        @Body nickname: NickNameChangeDTO,
    ): Call<NickNameChangeDTO>

    //회원 탈퇴
    @DELETE("freepre/user/delete/{userEmail}")
    fun deleteUser(
        @Path("userEmail")userEmail: String,
    ): Call<Unit>

    //사용자 주기 존재 여부
    @GET("freepre/user/cycle/{userEmail}")
    fun userCycle(
        @Path("userEmail")userEmail:String,
    ): Call<UserCycleDTO>


    //사용자 정보 가져오기
    @GET("freepre/user/info/{userEmail}")
    fun userInfo(
        @Path("userEmail")userEmail:String,
    ): Call<UserInfoDTO>
}