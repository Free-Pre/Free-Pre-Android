package com.example.free_pre_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//증상입력
data class SymptomCheckDTO(

    @SerializedName("email")
    val email: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("vomit")
    val vomit: Boolean,
    @SerializedName("headache")
    val headache: Boolean,
    @SerializedName("backache")
    val backache: Boolean,
    @SerializedName("constipation")
    val constipation: Boolean,
    @SerializedName("giddiness")
    val giddiness: Boolean,
    @SerializedName("tiredness")
    val tiredness: Boolean,
    @SerializedName("fainting")
    val fainting: Boolean,
    @SerializedName("sensitivity")
    val sensitivity: Boolean,
    @SerializedName("acne")
    val acne: Boolean,
    @SerializedName("muscular_pain")
    val muscular_pain: Boolean
): Serializable

//증상 가져오기
data class SymptomGetDTO(
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    var result: ResultSymptom
): Serializable

data class ResultSymptom(
    @SerializedName("vomit")
    var vomit: Boolean,
    @SerializedName("headache")
    var headache: Boolean,
    @SerializedName("backache")
    var backache: Boolean,
    @SerializedName("constipation")
    var constipation: Boolean,
    @SerializedName("giddiness")
    var giddiness: Boolean,
    @SerializedName("tiredness")
    var tiredness: Boolean,
    @SerializedName("fainting")
    var fainting: Boolean,
    @SerializedName("sensitivity")
    var sensitivity: Boolean,
    @SerializedName("acne")
    var acne: Boolean,
    @SerializedName("muscular_pain")
    var muscular_pain: Boolean
): Serializable

//증상 수정하기
data class SymptomModifyDTO(
    @SerializedName("vomit")
    val vomit: Boolean,
    @SerializedName("headache")
    val headache: Boolean,
    @SerializedName("backache")
    val backache: Boolean,
    @SerializedName("constipation")
    val constipation: Boolean,
    @SerializedName("giddiness")
    val giddiness: Boolean,
    @SerializedName("tiredness")
    val tiredness: Boolean,
    @SerializedName("fainting")
    val fainting: Boolean,
    @SerializedName("sensitivity")
    val sensitivity: Boolean,
    @SerializedName("acne")
    val acne: Boolean,
    @SerializedName("muscular_pain")
    val muscular_pain: Boolean
): Serializable


