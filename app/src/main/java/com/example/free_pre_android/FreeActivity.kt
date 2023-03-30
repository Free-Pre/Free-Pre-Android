package com.example.free_pre_android

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityFreeBinding
import com.example.free_pre_android.model.GlobalVariable

class FreeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeBinding

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mShakeDetector: ShakeDetector? = null
    val calendarFragment = CalendarFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFreeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //shake event
        // ShakeDetector 초기화
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector()
        mShakeDetector!!.setOnShakeListener(object : ShakeDetector.OnShakeListener {
            override fun onShake(count: Int) {
                //감지시 할 작업 작성
                val intent = Intent(this@FreeActivity, CameraActivity::class.java)
                startActivity(intent)
            }
        })

        //하단 bottom 네비게이션바
        //처음 LeftEdit VERSION
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id,FreeHomeFragment())
            .commitAllowingStateLoss()


        //BottomNav - Home 부분
        viewBinding.btnHome.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,FreeHomeFragment())
                .commitAllowingStateLoss()

            //페이지 애니메이션 - 왼쪽에서 오른쪽으로 - 지워도 되고
            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right)
        }

        //BottomNav - 캘린더 부분
        viewBinding.btnCalendar.setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,CalendarFragment())
                .commitAllowingStateLoss()
        }

        //BottomNav - How To Use 부분
        viewBinding.btnHtu.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,HowToUseFragment())
                .commitAllowingStateLoss()

        }

        //symptom에서 save 버튼 누르면 boolean 값 true로 바뀜
        //화면 전환시 캘린더 화면으로 전환됨
        if ((applicationContext as GlobalVariable).boolean){
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id,CalendarFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onRestart() {
        super.onRestart()
    }


    override fun onResume() {
        //Sensor Manager 등록을 취소
        mSensorManager!!.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id,FreeHomeFragment())
            .commitAllowingStateLoss()

        super.onResume()
    }

    // background 상황에서도 흔들림을 감지하고 적용할 필요는 없다
    override fun onPause() {
        //Sensor Manager 등록을 취소
        mSensorManager!!.unregisterListener(mShakeDetector)
        super.onPause()
    }
}

