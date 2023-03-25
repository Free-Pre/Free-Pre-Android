package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CalendarCheckResultDTO(
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    var result: List<Result>
): Serializable

data class Result(
    @SerializedName("period_id")
    var period_id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("end_date")
    val end_date: String
): Serializable


