package com.example.free_pre_android.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CalendarCheckResultDTO(
    @Expose
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @Expose
    @SerializedName("code")
    var code: Int,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("result")
    var result: Result
): Serializable

data class Result(
    @Expose
    @SerializedName("period_id")
    var period_id: Int,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("start_date")
    val start_date: String,
    @Expose
    @SerializedName("end_date")
    val end_date: String
): Serializable


