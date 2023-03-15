package com.example.free_pre_android

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.free_pre_android.databinding.ActivityGoogleMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {
    private lateinit var viewBinding: ActivityGoogleMapBinding
    val API_KEY = "AIzaSyCPTMTbInRNijlQfHmhrOpGQp1kyXHbFMA"

    /*접근 권한 부분
    private val permissionRequest = 99
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient      //GPS 받아오기
    lateinit var locationCallback: LocationCallback
    private var permissions = arrayOf(                                                 //권한 설정
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private var markerState: Marker? = null
    private var baseMarker: BitmapDescriptor? = null
    private var selectMarker: BitmapDescriptor? = null*/
    //---------------------------------------------------------------------------------------------------
    private lateinit var mMap: GoogleMap            // GoogleMap - 기본 지도 기능 및 데이터를 관리하기 위한 진입점
    private var selectedMarker:Marker?= null        //**

    lateinit var marker_root_view : View
    lateinit var tv_marker:TextView

    /*이렇게 한묶음 - marker_info_contents.xml꺼 지워도됨
    lateinit var marker_contents_view:View
    lateinit var tv_name:TextView        //병원이름
    lateinit var tv_address:TextView     //병원주소
    lateinit var tv_phone_number:TextView //병원번호*/

    /*
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }*/

    companion object{
        var latlngdata = arrayListOf<LatLngData>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGoogleMapBinding.inflate(layoutInflater)
        // 프래그먼트에 핸들 가져오기 및 콜백 등록하기

        setContentView(viewBinding.root)

        viewBinding.textContents.visibility = View.GONE


        //구글 지도 띄우기
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //상태바 색상 변경 - 나머지는 분홍색, 로그인 부분만 남색
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)


        /*권한 확인 코드
        if (!isPermitted()) {
            ActivityCompat.requestPermissions(this, permissions, permissionRequest)
        }*/



    }

    /*권한 확인 함수 - 위치 권한 설정 뜨도록
    private fun isPermitted():Boolean{
        for (perm in permissions){
            if(ContextCompat.checkSelfPermission(this,perm) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }*/

    //1.지도 준비 띄우기
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 초기 위치 설정 및 마커 표시 -마포보건소쪽...?
        //val latLng = LatLng(37.566168, 126.901609)
        //mMap.addMarker(MarkerOptions().position(latLng).title("여기"))   //마커가 추가됨.
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(35.833687, 127.111402),7.5f))   //초기 포커스 위치 - 전주
        mMap.setOnMarkerClickListener(this)
        mMap.setOnMapClickListener(this)

        setCustomMarkerView()
        getSampleMarkerItemts()

        /*GPS 받아오기
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this) //gps 자동으로 받아오기
        setUpdateLocationListener() // 현재 위치 업데이트 -함수로*/

    }

    /*
    //GPS 받아오기
    @SuppressLint("MissingPermission")
    fun setUpdateLocationListener() {
        val locationRequest =
            com.google.android.gms.location.LocationRequest.Builder(
                PRIORITY_HIGH_ACCURACY, //높은 정확도
                5000000 //5초에 한번씩 GPS 요청
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

    //현재위치가 필요할까? - 필요X
    fun setLastLocation(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)     //현재위치
        //현재위치 띄우기
        val markerOptions =
            MarkerOptions()
                .position(myLocation)
                .title("현재 위치")  //현재위치 띄우기

        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 17.0F)) // 현재 위치, 지도 크기
    }*/

    //2. onCreate()나 onMapReady()처럼 액티비티가 초기화되는 부분에 미리 뷰 설정해줌
    fun setCustomMarkerView(){

        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker_layout,null)  //framelayout과 연결
        tv_marker= marker_root_view.findViewById(R.id.tv_marker)   //marker 띄우기


        //필요없음 - marker_info_contents 부분이다. 제거해도 되는 부분!!
        /*marker_contents_view = LayoutInflater.from(this).inflate(R.layout.maker_info_contents,null)
        tv_name = marker_contents_view.findViewById(R.id.text_view_title)
        tv_address = marker_contents_view.findViewById(R.id.text_view_address)
        tv_phone_number = marker_contents_view.findViewById(R.id.text_view_phone_number)*/


    }

    //3. latlngData를 addMaker로 호출한다.
    fun getSampleMarkerItemts(){
        //장애친화 산부인과 마커 데이터 생성
        var latlngdata = arrayListOf<LatLngData>()

        //병원 이름이랑 주소도 영어로 해야하는지..? (회의**)
        latlngdata.add(LatLngData("1", LatLng(35.178621,128.092058),"진주고려병원","055-751-2500","경남 진주시 동진로 2"))    //진주고려병원
        latlngdata.add(LatLngData("2",LatLng(35.150103,126.851141),"미즈피아병원","062-380-2000","광주 서구 시청로 17"))
        latlngdata.add(LatLngData("3",LatLng(35.210319,126.873852),"빛고을여성병원","062-602-9000","광주 북구 하서로 395"))
        latlngdata.add(LatLngData("4",LatLng(36.322150,127.420492),"대전성모병원","1577-0888","대전 중구 대흥로 64"))
        latlngdata.add(LatLngData("5",LatLng(34.808934,126.431894),"목포미즈아이병원","0507-1436-8003","전남 목포시 백년대로 418"))
        latlngdata.add(LatLngData("6",LatLng(34.953409,127.522767),"현대여성아동병원","061-720-1111","전남 순천시 장선배기1길 8"))
        latlngdata.add(LatLngData("7",LatLng(34.636796,126.757982),"전라남도 강진의료원","061-433-2167","전남 강진군 강진읍 탐진로 5"))
        latlngdata.add(LatLngData("8",LatLng(34.765973,127.663520),"여수제일병원","061-689-8114","전남 여수시 쌍봉로 70"))
        latlngdata.add(LatLngData("9",LatLng(35.846917,127.141109),"전북대학교병원","063-250-1114","전북 전주시 덕진구 건지로 20 전북대학교병원"))
        latlngdata.add(LatLngData("10",LatLng(35.814269,127.133328),"예수병원","063-230-8114","전북 전주시 완산구 서원로 365 예수병원"))
        latlngdata.add(LatLngData("11",LatLng(35.803307,127.102724),"미르피아여성병원","063-211-1004","전북 전주시 완산구 쑥고개로 343"))
        latlngdata.add(LatLngData("12",LatLng(35.842745,127.124392),"한나여성병원","063-250-3500","전북 전주시 덕진구 기린대로 489 한나여성의원"))
        latlngdata.add(LatLngData("13",LatLng(35.830954,127.158624),"한별여성병원","063-244-3559","전북 전주시 덕진구 견훤로 215"))
        //시각장애인 전용 텍스트 제공
        latlngdata.add(LatLngData("14",LatLng(37.610503,126.929216),"최윤영산부인과","02-382-0220","서울특별시 은평구 통일로 731"))
        latlngdata.add(LatLngData("15",LatLng(35.159458,126.883789),"미래와희망산부인과","062-361-3344","광주광역시 서구 죽봉대로 68"))
        latlngdata.add(LatLngData("16",LatLng(35.159458,126.883789),"삼성미즈산부인과","032-516-3838","인천시 부평구 부평1동 465-2"))
        latlngdata.add(LatLngData("17",LatLng(37.516844,127.020487),"제이엘산부인과","02-514-1021","서울시 강남구 신사동 514-1번지 동원빌딩2층"))
        latlngdata.add(LatLngData("18",LatLng(37.265906,127.001679),"미스미즈산부인과","031-255-0903","경기도 수원시 팔달구 매산로1가 57-105 비전포에버상가 304"))
        latlngdata.add(LatLngData("19",LatLng(36.860125,127.439912),"미래산부인과"," 043-533-1223","충청북도 진천군 진천읍 읍내리 650번지"))
        latlngdata.add(LatLngData("20",LatLng(37.410916,127.129225),"이유미산부인과","031-783-8770","경기도 성남시 분당구 성남대로 912 BYC건물 4층"))
        latlngdata.add(LatLngData("21",LatLng(37.491073,126.893655),"미래사랑산부인과","02-837-7511","서울시 구로구 구로6동 148-13 미래사랑산부인과"))


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
            tv_marker.setTextColor(Color.parseColor("#FDE3F4")) //light
        } else {
            //그렇지 않을 경우
            tv_marker.setBackgroundResource(R.drawable.img_unselected_marker)
            tv_marker.setTextColor(Color.parseColor("#1A2A46")) //dark
        }

        var markerOptions = MarkerOptions() //
        markerOptions.position(latlngdata.latlng)
        markerOptions.title(latlngdata.tag)         //이거 안해서 작동 안했음...
        markerOptions.snippet(latlngdata.address + "\n"+ latlngdata.phoneNum)    //snippet에 주소와 전화번호 담음


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
        val phoneNum:String = marker.snippet as String
        val address:String = marker.snippet as String
        val temp = LatLngData(id, latlng, tag!!, phoneNum, address)

        return addMarker(temp, isSelectedMarker)
    }


    //6.
    //마커가 클릭되면 현재 클릭된 마커를 지도의 가운데로 이동시키고
    //클릭된 마커를 선택된 마커로 변환해줌
    override fun onMarkerClick(marker: Marker): Boolean {
        var center:CameraUpdate = CameraUpdateFactory.newLatLng(marker.position)
        mMap.animateCamera(center)
        changeSelectedMarker(marker)

        //상세 정보 띄우기 부분
        viewBinding.textContents.visibility = View.VISIBLE
        var name = findViewById<TextView>(R.id.text_view_title)
        var contents = findViewById<TextView>(R.id.text_view_address_and_phone)


        /*if (name.text == marker.title){
            return true
        }else{
            name.text = marker.title
            contents.text = marker.snippet
        }*/

        //클릭했을 때마다 문자 생성되는거 막는다.(if로) (**중복문제**)
        //근데 다른데 갔다오면 추가 생성되어 있는데..?
        if (name.text != marker.title){     //병원명이 같지 않다면 변경해줌
            name.text = marker.title
            contents.text = marker.snippet
        }else{                              //병원명이 같다면 동작X
            name.text = null
            contents.text = null
        }

        //name.text = marker.title
        //contents.text = marker.snippet

        //marker_contents_view.visibility = View.VISIBLE
        /*오류남.....
        marker_contents_view.visibility = View.VISIBLE
        var name = findViewById<TextView>(R.id.text_view_title)
        var address = findViewById<TextView>(R.id.text_view_address)
        var phoneNumber = findViewById<TextView>(R.id.text_view_phone_number)
        name.text = marker.title
        address.text = marker.snippet          //이공간을 아직 안만들어줘서.!?*/

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
            marker.remove()
        }
    }

    //8.
    //맵을 클릭했을 땐 선택된 마커를 제거해줘야한다.
    override fun onMapClick(latLng: LatLng) {
        changeSelectedMarker(null)               //** 색상 마커 선택 -> 선택 지워짐
        viewBinding.textContents.visibility = View.GONE  //지도를 선택하면 상세정보창 사라진다.
    }



    //위도 경도로 주소 구하는 Reverse-GeoCoding
    //Reverse-GeoCoding 사용해서 바꿔보기 (이게 위도/경도로 주소 구하는거다)
    private fun getAddress(location: Location): String {
        return try {
            with(Geocoder(this, Locale.KOREA).getFromLocation(location.latitude, location.longitude, 1)!!.first()){
                getAddressLine(0)   //주소
                countryName     //국가이름 (대한민국)
                countryCode     //국가코드
                adminArea       //행정구역 (서울특별시)
                locality        //관할구역 (중구)
                thoroughfare    //상세구역 (봉래동2가)
                featureName     //상세주소 (122-21)

            }
        } catch (e: Exception){
            e.printStackTrace()
            getAddress(location)
        }
    }

    //클릭이 아니라 터치로 바뀌어야 하나..?!
    //색상 바뀌기 전에 안내가 끝남




}








