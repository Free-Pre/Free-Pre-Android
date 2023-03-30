package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SettingInfoDTO(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("result")
    val result:UserDTO
) : Serializable

data class UserDTO(
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("notice")
    val notice:Boolean,
    @SerializedName("pregnancy")
    val pregnancy:Boolean
)