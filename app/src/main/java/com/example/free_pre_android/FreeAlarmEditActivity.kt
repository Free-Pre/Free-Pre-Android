package com.example.free_pre_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.free_pre_android.Alarm.Alarm
import com.example.free_pre_android.Alarm.AppDatabase
import com.example.free_pre_android.databinding.ActivityFreeAlarmEditBinding
import kotlinx.coroutines.*

class FreeAlarmEditActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFreeAlarmEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding=ActivityFreeAlarmEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnStart.setOnClickListener {
            if(viewBinding.btnStart.text=="AM")
                viewBinding.btnStart.text="PM"
            else
                viewBinding.btnStart.text="AM"
        }
        viewBinding.btnEnd.setOnClickListener {
            if(viewBinding.btnEnd.text=="AM")
                viewBinding.btnEnd.text="PM"
            else
                viewBinding.btnEnd.text="AM"
        }
        viewBinding.btnSave.setOnClickListener {
            val start:String
            val end:String
            val cycle:String
            if(viewBinding.editCycle.text==null||viewBinding.editStartHour.text==null||viewBinding.editStartMin.text==null||viewBinding.editEndHour.text==null||viewBinding.editEndMin.text==null||viewBinding.editCycle.text==null){
                Toast.makeText(this,"please enter all data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val cycle_int=viewBinding.editCycle.text.toString().toInt()
            var start_hour=viewBinding.editStartHour.text.toString().toInt()
            val start_min=viewBinding.editStartMin.text.toString().toInt()
            var end_hour=viewBinding.editEndHour.text.toString().toInt()
            val end_min=viewBinding.editEndMin.text.toString().toInt()

            if(start_hour<1||start_hour>12||end_hour<1||end_hour>12||start_min>59||end_min>59||cycle_int>23){
                Toast.makeText(this,"please enter available time",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(viewBinding.btnStart.text=="PM") {
            start_hour+=12
            }
            if(viewBinding.btnEnd.text=="PM") {
                end_hour+=12
            }
            if(start_hour*100+start_min>=end_hour*100+end_min){
                Toast.makeText(this,"End time can't be less than start time",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            start="${setNumber(start_hour)}:${setNumber(start_min)}:00"
            end="${setNumber(end_hour)}:${setNumber(end_min)}:00"
            cycle=setNumber(cycle_int)
            val alarm=Alarm(0,start,end,cycle)
            addAlarm(alarm)
        }
    }

    fun setNumber(a:Int):String{
        return if(a<10)
            "0${a}"
        else
            a.toString()
    }
    fun addAlarm(alarm: Alarm){
        val db: AppDatabase= Room.databaseBuilder(
            applicationContext,AppDatabase::class.java,"AlarmDB"
        ).build()
        val alarmDao=db.alarmDao()
        CoroutineScope(Dispatchers.IO).launch() {//동기 처리
            launch {
                alarmDao.addAlarm(alarm)
                Log.d("ADD_ALARM", alarm.toString())
            }.join()
            launch { finish() }
        }
    }
}