package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//월경일 추가
data class PeriodAddDTO(
    val email:String,
    val start_date:String,
    val end_date:String
): Serializable
data class PeriodAddResultDTO(
    val isSuccess:Boolean,
    val code:Int,
    val message:String,
    val result:PeriodAddResult
) : Serializable
data class PeriodAddResult(
    @SerializedName("period_id")
    val period_id:Int,
    @SerializedName("email")
    val email:String,
    @SerializedName("start_date")
    val start_date:String,
    @SerializedName("end_date")
    val end_date:String
) : Serializable

//월경일 리스트 가져오기

data class PeriodListResultDTO(
    @SerializedName("isSuccess")
    val isSuccess:Boolean,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("result")
    val result:MutableList<PeriodListResult>
):Serializable

data class PeriodListResult(
    @SerializedName("period_id")
    val period_id:Int,
    @SerializedName("email")
    val email:String,
    @SerializedName("start_date")
    val start_date:String,
    @SerializedName("end_date")
    val end_date:String
):Serializable
