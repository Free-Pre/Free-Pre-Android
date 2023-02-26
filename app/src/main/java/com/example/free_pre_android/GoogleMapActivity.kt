package com.example.free_pre_android

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.free_pre_android.databinding.ActivityGoogleMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {
    private lateinit var viewBinding: ActivityGoogleMapBinding
    val API_KEY = "AIzaSyCPTMTbInRNijlQfHmhrOpGQp1kyXHbFMA"

    //접근 권한 부분
    private val permissionRequest = 99
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient      //GPS 받아오기
    lateinit var locationCallback: LocationCallback
    private var permissions = arrayOf(                                                 //권한 설정
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private var markerState: Marker? = null
    private var baseMarker: BitmapDescriptor? = null
    private var selectMarker: BitmapDescriptor? = null
    //---------------------------------------------------------------------------------------------------
    private lateinit var mMap: GoogleMap            // GoogleMap - 기본 지도 기능 및 데이터를 관리하기 위한 진입점
    private var selectedMarker:Marker?= null        //**
    lateinit var marker_root_view : View
    lateinit var tv_marker:TextView

    companion object{
        var latlngdata = arrayListOf<LatLngData>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGoogleMapBinding.inflate(layoutInflater)
        // 프래그먼트에 핸들 가져오기 및 콜백 등록하기

        setContentView(viewBinding.root)

        //구글 지도 띄우기
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //상태바 색상 변경 - 나머지는 분홍색, 로그인 부분만 남색
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)


        //권한 확인 코드
        if (!isPermitted()) {
            ActivityCompat.requestPermissions(this, permissions, permissionRequest)
        }

    }

    //권한 확인 함수 - 위치 권한 설정 뜨도록
    private fun isPermitted():Boolean{
        for (perm in permissions){
            if(ContextCompat.checkSelfPermission(this,perm) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    //1.지도 준비 띄우기
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 초기 위치 설정 및 마커 표시 -마포보건소쪽...?
        val latLng = LatLng(37.566168, 126.901609)
        //mMap.addMarker(MarkerOptions().position(latLng).title("여기"))   //마커가 추가됨.
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.537523, 126.96558),14f))
        mMap.setOnMarkerClickListener(this)
        mMap.setOnMapClickListener(this)

        setCustomMarkerView()
        getSampleMarkerItemts()

        //GPS 받아오기
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this) //gps 자동으로 받아오기
        setUpdateLocationListener() // 현재 위치 업데이트 -함수로

    }
    //GPS 받아오기
    @SuppressLint("MissingPermission")
    fun setUpdateLocationListener() {
        val locationRequest =
            com.google.android.gms.location.LocationRequest.Builder(
                PRIORITY_HIGH_ACCURACY, //높은 정확도
                50000 //5초에 한번씩 GPS 요청
            ).build()

        //location 요청 함수 호출 (locationRequest, locationCallback)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.withIndex().forEach {
                    setLastLocation(it.value)
                }
            }
        }

        //location 요청 함수 호출 (locationRequest, locationCallback)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    //현재위치
    fun setLastLocation(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
        //현재위치 띄우기
        val markerOptions =
            MarkerOptions()
                .position(myLocation)
                .title("현재 위치")  //현재위치 띄우기

        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 17.0F)) // 현재 위치, 지도 크기
    }

    //2. onCreate()나 onMapReady()처럼 액티비티가 초기화되는 부분에 미리 뷰 설정해줌
    fun setCustomMarkerView(){

        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker_layout,null)  //framelayout과 연결
        tv_marker= marker_root_view.findViewById(R.id.tv_marker)   //marker 띄우기
        //selectMarker = null!!

    }

    //3. latlngData를 addMaker로 호출한다.
    fun getSampleMarkerItemts(){
        //장애친화 산부인과 마커 데이터 생성
        var latlngdata = arrayListOf<LatLngData>()

        latlngdata.add(LatLngData("1", LatLng(35.178621,128.092058),"진주고려병원"))
        latlngdata.add(LatLngData("2",LatLng(35.150103,126.851141),"미즈피아병원"))
        latlngdata.add(LatLngData("3",LatLng(35.210319,126.873852),"빛고을여성병원"))
        latlngdata.add(LatLngData("4",LatLng(36.322150,127.420492),"대전성모병원"))
        latlngdata.add(LatLngData("5",LatLng(34.808934,126.431894),"목포미즈아이병원"))
        latlngdata.add(LatLngData("6",LatLng(34.953409,127.522767),"현대여성아동병원"))
        latlngdata.add(LatLngData("7",LatLng(34.636796,126.757982),"전라남도 강진의료원"))
        latlngdata.add(LatLngData("8",LatLng(34.765973,127.663520),"여수제일병원"))
        latlngdata.add(LatLngData("9",LatLng(35.846917,127.141109),"전북대학교병원"))
        latlngdata.add(LatLngData("10",LatLng(35.814269,127.133328),"예수병원"))
        latlngdata.add(LatLngData("11",LatLng(35.803307,127.102724),"미르피아여성병원"))
        latlngdata.add(LatLngData("12",LatLng(35.842745,127.124392),"한나여성병원"))
        latlngdata.add(LatLngData("13",LatLng(35.830954,127.158624),"한별여성병원"))

        for (i in latlngdata.indices){
            addMarker(latlngdata[i],false)
        }
    }

    //4. 실제 마커를 추가하는 함수
    fun addMarker(latlngdata:LatLngData, isSelectedMarker:Boolean): Marker? {

        var list: List<Address>? = null
        if (isSelectedMarker) {
            //선택되어있는 마커로 표시되어야 할 경우
            tv_marker.setBackgroundResource(R.drawable.img_selected_marker)
            //tv_marker.setTextColor(R.color.primary_dark)
            //tv_marker.resources.getColor(R.color.primary_dark)
            tv_marker.setTextColor(Color.parseColor("#FDE3F4")) //light
        } else {
            //그렇지 않을 경우
            tv_marker.setBackgroundResource(R.drawable.img_unselected_marker)
            tv_marker.setTextColor(Color.parseColor("#1A2A46")) //dark
            //tv_marker.setTextColor(Color.WHITE)
        }

        var markerOptions = MarkerOptions() //
        markerOptions.position(latlngdata.latlng)
        markerOptions.title(latlngdata.tag)         //이거 안해서 작동 안했음...
        //markerOptions.snippet
        tv_marker.setText(latlngdata.tag)           //마커에 병원이름 띄움.

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view)))
        return mMap.addMarker(markerOptions)
    }
    //5. View를 Bitmap으로 변환함 - buildDrawingCache()를 통해서 Bitmap으로 변환할 수 있다.
   private fun createDrawableFromView(context: Context, view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        view.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()
        val bitmap: Bitmap = Bitmap.createBitmap(
            view.getMeasuredWidth(),
            view.getMeasuredHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    //마커를 다시 생성하기 위한 addMarker() 함수
    private fun addMarker(marker: Marker, isSelectedMarker: Boolean): Marker? {
        val id: String = marker.id
        val latlng: LatLng = marker.position
        val tag: String? = marker.title
        val temp = LatLngData(id, latlng, tag!!)

        return addMarker(temp, isSelectedMarker)
    }


    //6.
    //마커가 클릭되면 현재 클릭된 마커를 지도의 가운데로 이동시키고
    //클릭된 마커를 선택된 마커로 변환해줌
    override fun onMarkerClick(marker: Marker): Boolean {
        var center:CameraUpdate = CameraUpdateFactory.newLatLng(marker.position)
        mMap.animateCamera(center)
        changeSelectedMarker(marker)

        return true
    }

    //7.
    fun changeSelectedMarker(marker:Marker?){
        // 선택했던 마커 되돌리기
        if (selectedMarker != null) {
            addMarker(selectedMarker!!, false);
            selectedMarker!!.remove()
        }

        // 선택한 마커 표시
        if (marker != null) {
            selectedMarker = addMarker(marker, true);
            marker.remove();
        }
    }

    //8.
    //맵을 클릭했을 땐 선택된 마커를 제거해줘야한다.
    override fun onMapClick(latLng: LatLng) {
        changeSelectedMarker(null)               //**
    }



    //오늘 해야될 부분

    //[구글맵] 2월 21일까지 마무리하기
    //현재 위치 권한 설정 -완료
    //산부인과 마커 띄우기 -완료
    //전화번호, 길찾기 부분까지 해결하면 좋을 듯
    //전화번호, 영업시간등의 정보를 전달하는데 집중하면 좋을 듯!(길찾기보다 - 길찾기는 사실상 구글맵이 더 좋음)
    //마크 모양 바꾸기 -완료
    //탭했을 때 다이얼로그 띄우면 좋을 듯하다..
    //문제 - 현재위치 클릭했을 때 마크 새롭게 표시 안되도록 되었으면 좋겠다..


    //마커 클릭시 다이얼로그 정보 나오도록 만들기!!!
    //길찾기 누르면 구글맵으로 데이터 전달 가능한가?

    //[정보부분] 2월 23일까지 마무리리
   //정보들 정리해서
    //레이아웃에 깔끔하게 넣기
    //음성 + 글자 깔끔하게 정리해서


    //알람 설정하는 부분 만들기! -이번주까지 마무리하기


}








