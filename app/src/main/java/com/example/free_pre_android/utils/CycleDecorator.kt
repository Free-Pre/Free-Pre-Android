package com.example.free_pre_android.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

//생리 주기
class CycleDecorator(val context: Context, val dates: List<String>): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains("${day.year}-${String.format("%02d", day.month)}-${String.format("%02d", day.day)}")
    }

    override fun decorate(view: DayViewFacade) {
        view?.addSpan(object: StyleSpan(Typeface.BOLD){})
        view?.addSpan(object: RelativeSizeSpan(1.4f){})
        view?.addSpan(object: ForegroundColorSpan(Color.parseColor("#E80000")){})
    }

}