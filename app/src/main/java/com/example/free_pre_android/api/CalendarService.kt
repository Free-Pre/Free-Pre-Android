package com.example.free_pre_android.api

import com.example.free_pre_android.data.CalendarCheckResultDTO
import retrofit2.Call
import retrofit2.http.*

interface CalendarService {
    //캘린더
    @GET("freepre/period/calendar/{userEmail}/{month}")
    fun calendarCheck(
        @Path("userEmail")userEmail:String,
        @Path("month")month:String
    ): Call<CalendarCheckResultDTO>
}