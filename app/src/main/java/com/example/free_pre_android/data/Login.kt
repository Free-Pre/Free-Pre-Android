package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// 로그인
data class emailCheckDTO(
    @SerializedName("isSuccess")
    val isSeccess: Boolean,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("result")
    val result:Boolean
) : Serializable