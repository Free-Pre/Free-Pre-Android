package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class VersionChangeDTO(
    @SerializedName("first_period")
    val first_period:Boolean
):Serializable
data class VersionChangeResultDTO(
    @SerializedName("isSuccess")
    val isSuccess:Boolean,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("result")
    val result:String
):Serializable