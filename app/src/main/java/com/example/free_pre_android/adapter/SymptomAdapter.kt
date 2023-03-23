package com.example.free_pre_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.free_pre_android.R
import com.example.free_pre_android.databinding.ListitemSymptomBinding
import com.example.free_pre_android.model.SymptomData


class SymptomAdapter(private val items: ArrayList<SymptomData>) : RecyclerView.Adapter<SymptomAdapter.ViewHolder>(/*DiffUtils*/) {

    public var selectedSymptom = arrayListOf<SymptomData>()


    inner class ViewHolder(
        private val binding: ListitemSymptomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SymptomData) {
            with(binding) {
                //item.symptom = textSymptom.text.toString()
                textSymptom.text = item.symptom               //symptom이 각각의 증상들

            }
            binding.root.setOnClickListener {
                applySelection(binding, item)

            }
        }

    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: SymptomAdapter.ViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        //추가
        ViewHolder(ListitemSymptomBinding.inflate(LayoutInflater.from(parent.context), parent, false))



    /*
    private class DiffUtils : DiffUtil.ItemCallback<SymptomData>() {
        override fun areItemsTheSame(oldItem: SymptomData, newItem: SymptomData): Boolean {
            return oldItem.symptom == newItem.symptom
        }

        override fun areContentsTheSame(oldItem: SymptomData, newItem: SymptomData): Boolean {
            return oldItem.symptom == newItem.symptom
        }
    }*/

    private var onItemClickListener: ((SymptomData) -> Unit)? = null

    fun setOnItemClickListener(listener: (SymptomData) -> Unit) {
        onItemClickListener = listener
    }

    //선택했을시 색상 변경
    private fun applySelection(binding: ListitemSymptomBinding, item: SymptomData) {
        if (selectedSymptom.contains(item)) {
            selectedSymptom.remove(item)
            changeBackground(binding, R.color.primary_dark)
            changeTextColor(binding,R.color.primary_light)
            changeDrawable(binding,R.drawable.style_symptom_content)
        } else {
            selectedSymptom.add(item)
            changeBackground(binding, R.color.primary_light)
            changeTextColor(binding,R.color.primary_dark)
            changeDrawable(binding,R.drawable.style_symptom_content_dark)
        }
    }

    private fun changeBackground(binding: ListitemSymptomBinding, resId: Int) {
        binding.layoutContainer.setBackgroundColor(ContextCompat.getColor(binding.root.context, resId))


    }
    private fun changeTextColor(binding: ListitemSymptomBinding, resId: Int){
        binding.textSymptom.setTextColor(ContextCompat.getColor(binding.root.context, resId))
    }

    private fun changeDrawable(binding: ListitemSymptomBinding, drawableResId: Int){
        binding.textSymptom.setBackgroundResource(drawableResId)
    }

    fun getSelectedExpense() = selectedSymptom.size    //선택된 아이템 갯수

}




