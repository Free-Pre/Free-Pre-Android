package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.free_pre_android.Alarm.Alarm
import com.example.free_pre_android.Alarm.AppDatabase
import com.example.free_pre_android.adapter.AlarmListAdapter
import com.example.free_pre_android.databinding.ActivityFreeAlarmBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FreeAlarmActivity : AppCompatActivity(){
    private lateinit var viewBinding: ActivityFreeAlarmBinding
    companion object {
        var selectedAlarm=Alarm(-1)
        var selectedItem=-1
        lateinit var db:AppDatabase
    }
    val adapter = AlarmListAdapter(this)
    val alarmList:MutableList<Alarm> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding=ActivityFreeAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        db= Room.databaseBuilder(
            applicationContext,AppDatabase::class.java,"AlarmDB"
        ).build()
        getAlarmList()
        viewBinding.btnAdd.setOnClickListener {
            startActivity(Intent(this@FreeAlarmActivity, FreeAlarmEditActivity::class.java))
        }
    }
    override fun onRestart() {
        super.onRestart()
        getAlarmList()
    }
    fun getFcmToken(){
        // FCM 토큰 발급
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("FCM_TOKEN", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                val msg = token.toString()
                Log.d("FCM_TOKEN", msg)
                setSharedData("Fcm", "fcmKey", msg)
            }
        )
    }
    //sharedPreference
    fun setSharedData(name: String, key: String, data: String) {
        //Editor로 데이터 저장하기
        var sharedPreferences: SharedPreferences = getSharedPreferences(name, Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.apply()

        Log.d("FCM","sharedPreference:$data")   //값 잘 들어감
    }
    fun getAlarmList(){
        val alarmDao=db.alarmDao()
        var alarms:List<Alarm>
        alarmList.clear()
        CoroutineScope(Dispatchers.IO).launch {
            alarms=alarmDao.getAlarmList()
            for(info in alarms){
                var alarm=Alarm(
                    info.id,
                    info.start_time,
                    info.end_time,
                    info.alarm_gap,
                    info.selected
                )
                alarmList.add(alarm)
            }
            Log.d("ALARM_ROOM",alarms.toString())

            if(alarms.isEmpty()) {
                selectedAlarm.id = -1
                selectedItem = -1
            }

            if(alarms.isEmpty())
                selectedAlarm.id=-1
            selectedItem=-1

        }
        adapter.alarmList=alarmList
        viewBinding.recyAlarm.adapter=adapter
        viewBinding.recyAlarm.layoutManager = LinearLayoutManager(this)
        Log.d("ALARM_SELECTED", selectedAlarm.id.toString())
    }
    fun updateAlarm(alarm:Alarm,position:Int){
        val alarmDao=db.alarmDao()
        CoroutineScope(Dispatchers.IO).launch {
            alarmDao.updateAlarm(alarm)
        }
        adapter.notifyItemChanged(position)
        Log.d("UPDATE_ALARM",alarm.toString())
    }
    fun deleteAlarm(alarm:Alarm,position: Int){
        val alarmDao=db.alarmDao()
        CoroutineScope(Dispatchers.IO).launch {
            alarmDao.deleteAlarm(alarm)
        }
        alarmList.removeAt(position)
        adapter.notifyItemRemoved(position)
        Log.d("DELETE_ALARM",alarm.toString())
    }
}