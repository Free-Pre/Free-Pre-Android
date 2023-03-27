package com.example.free_pre_android.Alarm

import android.content.Context
import androidx.room.*

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo var id:Int,
    @ColumnInfo var start_time:String="",
    @ColumnInfo var end_time:String="",
    @ColumnInfo var alarm_gap:String="",
    @ColumnInfo var selected:Boolean=false
)

@Dao//Data Access Object
interface AlarmDao{
    //알람 리스트 조회
    @Query("SELECT * FROM alarm")
    fun getAlarmList():List<Alarm>
    //알람 저장
    @Insert
    fun addAlarm(vararg alarm:Alarm)
    //알람 편집
    @Update
    fun updateAlarm(vararg alarm:Alarm)
    //알람 삭제
    @Delete
    fun deleteAlarm(vararg alarm:Alarm)
}

@Database(entities = [Alarm::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun alarmDao():AlarmDao

    /*companion object{
        /* @Volatile = 접근가능한 변수의 값을 cache를 통해 사용하지 않고
        thread가 직접 main memory에 접근 하게하여 동기화. */
        @Volatile
        private var instance : AppDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : AppDatabase? {
            if(instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "alarmDB"
                    ).build()
                }
            }
            return instance
        }
    }*/
}