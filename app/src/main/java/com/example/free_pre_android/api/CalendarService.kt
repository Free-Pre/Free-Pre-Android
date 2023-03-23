package com.example.free_pre_android.api

import com.example.free_pre_android.data.CalendarCheckResultDTO
import retrofit2.Call
import retrofit2.http.*

interface CalendarService {
    //캘린더
    @GET("freepre/user/{userEmail}/{month}")
    fun calendarCheck(
        @Path("email")user_email:String,
        @Path("month")user_month:String
    ): Call<CalendarCheckResultDTO>

}