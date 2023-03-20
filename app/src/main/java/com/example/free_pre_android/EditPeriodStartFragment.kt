package com.example.free_pre_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.free_pre_android.databinding.FragmentEditPeriodStartBinding

class EditPeriodStartFragment : Fragment() {

    var mainActivity:EditPeriodActivity?=null
    private lateinit var viewBinding: FragmentEditPeriodStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentEditPeriodStartBinding.inflate(layoutInflater)
        viewBinding.editYear.addTextChangedListener {
            mainActivity?.start_year=viewBinding.editYear.text.toString()
        }
        viewBinding.editMonth.addTextChangedListener {
            mainActivity?.start_month=viewBinding.editMonth.text.toString()
        }
        viewBinding.editDay.addTextChangedListener {
            mainActivity?.start_day=viewBinding.editDay.text.toString()
        }
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is EditPeriodActivity) mainActivity=context
        Log.d("EDIT_PERIOD_START","onAttach")
    }
}