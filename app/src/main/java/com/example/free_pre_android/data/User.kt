package com.example.free_pre_android.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable


//닉네임 변경
data class NickNameChangeDTO(
    @SerializedName("nickname")
    val nickname: String
):Serializable

//회원 탈퇴
data class DeleteUserDTO(
    @SerializedName("nickname")
    val nickname: String
):Serializable


//사용자 주기 존재여부 - 사용 안해도 됨.
data class UserCycleDTO(
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    var result: Boolean
):Serializable

//사용자 정보 가져오기 - Setting 부분
data class UserInfoDTO(
    @SerializedName("isSuccess")
    var isSuccess: Boolean,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    var result: ResultUserInfo
):Serializable

data class ResultUserInfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("first_period")
    var first_period: Boolean,
    @SerializedName("cycle")
    var cycle: Int,
    @SerializedName("term")
    var term: Int,
    @SerializedName("notice")
    var notice: Boolean,
    @SerializedName("pregnancy")
    var pregnancy: Boolean
):Serializable


