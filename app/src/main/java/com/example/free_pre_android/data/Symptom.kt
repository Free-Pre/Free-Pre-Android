package com.example.free_pre_android.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//증상입력
data class SymptomCheckDTO(

    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("date")
    val date: String,
    @Expose
    @SerializedName("vomit")
    val vomit: String,
    @Expose
    @SerializedName("headache")
    val headache: String,
    @Expose
    @SerializedName("backache")
    val backache: String,
    @Expose
    @SerializedName("constipation")
    val constipation: String,
    @Expose
    @SerializedName("giddiness")
    val giddiness: String,
    @Expose
    @SerializedName("tiredness")
    val tiredness: String,
    @Expose
    @SerializedName("fainting")
    val fainting: String,
    @Expose
    @SerializedName("sensitivity")
    val sensitivity: String,
    @Expose
    @SerializedName("acne")
    val acne: String,
    @Expose
    @SerializedName("muscular_pain")
    val muscular_pain: String
): Serializable

//증상 가져오기
data class SymptomGetDTO(
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
    var result: ResultSymptom
): Serializable

data class ResultSymptom(
    @Expose
    @SerializedName("vomit")
    var vomit: Boolean,
    @Expose
    @SerializedName("headache")
    var headache: Boolean,
    @Expose
    @SerializedName("backache")
    var backache: Boolean,
    @Expose
    @SerializedName("constipation")
    var constipation: Boolean,
    @Expose
    @SerializedName("giddiness")
    var giddiness: Boolean,
    @Expose
    @SerializedName("tiredness")
    var tiredness: Boolean,
    @Expose
    @SerializedName("fainting")
    var fainting: Boolean,
    @Expose
    @SerializedName("sensitivity")
    var sensitivity: Boolean,
    @Expose
    @SerializedName("acne")
    var acne: Boolean,
    @Expose
    @SerializedName("muscular_pain")
    var muscular_pain: Boolean
): Serializable