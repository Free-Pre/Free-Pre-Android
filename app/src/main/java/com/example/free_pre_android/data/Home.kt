package com.example.free_pre_android.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable

//cycle,term,최근 월경 시작 날짜 가져오기
data class HomeInfoDTO(
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    var result: ResultHomeInfo
):Serializable

data class ResultHomeInfo(
    @SerializedName("cycle")
    var cycle: Int,                  //주기-28
    @SerializedName("term")          //기간-7
    var term: Int,
    @SerializedName("start_date")    //월경 시작 날짜
    val start_date: String,
    @SerializedName("end_date")    //월경 시작 날짜
    val end_date: String
)


