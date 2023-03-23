package com.example.free_pre_android.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.free_pre_android.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DayStateDecorator(val context: Context, val dates: List<String>): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains("${day.year}-${String.format("%02d", day.month)}-${String.format("%02d", day.day)}")
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5f, ContextCompat.getColor(context, R.color.primary_yellow)))
    }

}