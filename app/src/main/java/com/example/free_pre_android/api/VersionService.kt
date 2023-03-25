package com.example.free_pre_android.api

import com.example.free_pre_android.data.CycleCheckResultDTO
import com.example.free_pre_android.data.VersionChangeDTO
import com.example.free_pre_android.data.VersionChangeResultDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface VersionService {
    //Pre -> Free
    @PATCH("freepre/user/version/{userEmail}")
    fun versionChange(
        @Path ("userEmail") userEmail:String,
        @Body first_period:VersionChangeDTO,
    ): Call<VersionChangeResultDTO>

    @GET("freepre/user/cycle/{userEmail}")
    fun cycleCheck(
        @Path("userEmail")userEmail: String
    ):Call<CycleCheckResultDTO>
}