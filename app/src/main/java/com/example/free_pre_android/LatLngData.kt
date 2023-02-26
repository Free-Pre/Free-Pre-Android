package com.example.free_pre_android

import com.google.android.gms.maps.model.LatLng

//마커 데이터
data class LatLngData(
    val id: String,      //아이디 - 병원 갯수 파악
    val latlng: LatLng,  //위도&경도
    val tag:String,      //병원명
    //val address: String,  //병원주소
    //val phoneNumber:String //병원전화번호

)
