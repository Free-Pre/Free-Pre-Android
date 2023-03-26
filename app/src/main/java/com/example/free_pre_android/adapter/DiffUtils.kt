package com.example.free_pre_android.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.free_pre_android.model.SymptomData

object DiffUtils : DiffUtil.ItemCallback<SymptomData>() {
    override fun areItemsTheSame(oldItem: SymptomData, newItem: SymptomData): Boolean {
        return oldItem.symptom == newItem.symptom
    }

    override fun areContentsTheSame(oldItem: SymptomData, newItem: SymptomData): Boolean {
        return oldItem.symptom != newItem.symptom
    }
}