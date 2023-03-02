package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// 로그인 이메일 확인
data class emailCheckResultDTO(
    @SerializedName("isSuccess")
    val isSeccess: Boolean,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("result")
    val result:Boolean
) : Serializable

//Pre 회원가입
data class preSignUpDTO(
    val email:String,
    val nickname:String,
    val first_period:Boolean
):Serializable