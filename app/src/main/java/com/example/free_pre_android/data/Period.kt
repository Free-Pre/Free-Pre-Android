package com.example.free_pre_android.data

import java.io.Serializable

//월경일 추가
data class PeriodAddDTO(
    val email:String,
    val start_date:String,
    val end_date:String
): Serializable