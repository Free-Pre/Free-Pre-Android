package com.example.free_pre_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentEditPeriodEndBinding

class EditPeriodEndFragment : Fragment() {

    var mainActivity:EditPeriodActivity?=null
    var end_date=""
    var period_id=-1
    private lateinit var viewBinding: FragmentEditPeriodEndBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentEditPeriodEndBinding.inflate(layoutInflater)
        viewBinding.editYear.addTextChangedListener {
            mainActivity?.end_year=viewBinding.editYear.text.toString()
        }
        viewBinding.editMonth.addTextChangedListener {
            mainActivity?.end_month=viewBinding.editMonth.text.toString()
        }
        viewBinding.editDay.addTextChangedListener {
            mainActivity?.end_day=viewBinding.editDay.text.toString()
        }
        if(period_id!=-1){
            viewBinding.editYear.setText(end_date.substring(0,4))
            viewBinding.editMonth.setText(end_date.substring(5,7))
            viewBinding.editDay.setText(end_date.substring(8))
        }
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is EditPeriodActivity) mainActivity=context
        end_date= mainActivity?.end_date!!
        period_id=mainActivity?.period_id!!
        Log.d("EDIT_PERIOD_END","onAttach $end_date" )
    }
    override fun onResume() {
        super.onResume()
        Log.d("EDIT_PERIOD_END","onResume")
    }
}