package com.example.free_pre_android.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.free_pre_android.Alarm.Alarm
import com.example.free_pre_android.FreeAlarmActivity
import com.example.free_pre_android.databinding.ListitemAlarmBinding
import com.example.free_pre_android.FreeAlarmActivity.Companion.selectedAlarm
import com.example.free_pre_android.FreeAlarmActivity.Companion.selectedItem
import kotlinx.coroutines.*


class AlarmListAdapter(val contex: FreeAlarmActivity ) : RecyclerView.Adapter<Holder>() {
    var alarmList = mutableListOf<Alarm>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ListitemAlarmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding,contex)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val alarm=alarmList.get(position)
        if(alarm.selected){
            selectedAlarm=alarm
            selectedItem=position
        }
        holder.setAlarm(alarm)
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

}
class Holder(val binding:ListitemAlarmBinding,val contex: FreeAlarmActivity):RecyclerView.ViewHolder(binding.root) {
    lateinit var rb: RadioButton
    lateinit var db: Button
    fun setAlarm(alarm: Alarm) {
        val position = this.bindingAdapterPosition
        rb = binding.radioHour
        db = binding.btnDelete
        val start = alarm.start_time.subSequence(0, 5)
        val end = alarm.end_time.subSequence(0, 5)
        val gap = alarm.alarm_gap.subSequence(0, 2)

        binding.radioHour.text = "$start ~ $end\n$gap hours"
        binding.radioHour.isChecked = alarm.selected

        binding.radioHour.setOnClickListener {
            if (selectedItem == -1) {//지금 true인게 없는 상황
                binding.radioHour.isChecked = true
                alarm.selected = true
                contex.updateAlarm(alarm, position)
            } else if (selectedItem == position) {
                binding.radioHour.isChecked = false
                alarm.selected = false
                contex.updateAlarm(alarm, position)
                selectedItem = -1
                selectedAlarm.id = -1
            } else {
                binding.radioHour.isChecked = true
                alarm.selected = true
                selectedAlarm.selected = false
                /*CoroutineScope(Dispatchers.IO).launch() {//동기 처리
                    launch {
                        contex.updateAlarm(alarm,position)
                    }.join()
                    launch {
                        contex.updateAlarm(selectedAlarm, selectedItem)
                    }.join()
                    launch {
                        selectedAlarm=alarm
                        selectedItem=position
                    }
                }*/
                contex.updateAlarm(alarm, position)
                contex.updateAlarm(selectedAlarm, selectedItem)
                selectedAlarm = alarm
                selectedItem = position
            }
            //retrotit 연결

        }
        binding.btnDelete.setOnClickListener {
            contex.deleteAlarm(alarm,position)
        }
    }
}