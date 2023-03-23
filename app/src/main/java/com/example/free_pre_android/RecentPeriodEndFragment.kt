package com.example.free_pre_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.free_pre_android.databinding.FragmentRecentPeroidEndBinding


class RecentPeriodEndFragment : Fragment() {
    var mainActivity:RecentPeriodActivity?=null
    private lateinit var viewBinding: FragmentRecentPeroidEndBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentRecentPeroidEndBinding.inflate(layoutInflater)
        viewBinding.editYear.addTextChangedListener {
            mainActivity?.end_year=viewBinding.editYear.text.toString()
        }
        viewBinding.editMonth.addTextChangedListener {
            mainActivity?.end_month=viewBinding.editMonth.text.toString()
        }
        viewBinding.editDay.addTextChangedListener {
            mainActivity?.end_day=viewBinding.editDay.text.toString()
        }
        return viewBinding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is RecentPeriodActivity) mainActivity=context
        Log.d("RECENT_PERIOD_END","onAttach")
    }
}