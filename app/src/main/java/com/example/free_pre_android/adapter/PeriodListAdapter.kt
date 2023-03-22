package com.example.free_pre_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.free_pre_android.data.PeriodListResult
import com.example.free_pre_android.databinding.ListitemEditPeriodBinding
import java.text.SimpleDateFormat


class PeriodListAdapter(val context: Context):RecyclerView.Adapter<PeriodListHolder>() {
    var periodList= mutableListOf<PeriodListResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodListHolder {
        val binding =ListitemEditPeriodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PeriodListHolder(binding,context)
    }

    override fun onBindViewHolder(holder: PeriodListHolder, position: Int) {
        val periodDetail=periodList.get(position)
        holder.setPeriodDetail(periodDetail)
    }

    override fun getItemCount(): Int {
        return periodList.size
    }
}
class PeriodListHolder(val binding:ListitemEditPeriodBinding,val context: Context):RecyclerView.ViewHolder(binding.root){
    fun setPeriodDetail(periodDetail:PeriodListResult){
        var start_date = SimpleDateFormat("yyyy-MM-dd") .parse(periodDetail.start_date)
        var end_date = SimpleDateFormat("yyyy-MM-dd") .parse(periodDetail.end_date)
        var diffDays = (end_date.time - start_date.time) / (1000 * 60 * 60 * 24)
        binding.txtDate.text= periodDetail.start_date+"\n~ "+periodDetail.end_date
        binding.txtNumber.text=diffDays.toString()
        if(diffDays==1L)
            binding.txtDays.text="day"
    }
}