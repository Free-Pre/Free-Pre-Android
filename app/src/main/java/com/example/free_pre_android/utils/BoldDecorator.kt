package com.example.free_pre_android.utils

import android.graphics.Typeface
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

//생리 주기 부분
class BoldDecorator(min:CalendarDay, max:CalendarDay): DayViewDecorator {
    val maxDay = max           //생리 시작 날짜
    val minDay = min           //생리 끝 날짜
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return (day?.month == maxDay.month && day.day <= maxDay.day)
                || (day?.month == minDay.month && day.day >= minDay.day)
                || (minDay.month < day?.month!! && day.month < maxDay.month)
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: StyleSpan(Typeface.BOLD){})
        view?.addSpan(object: RelativeSizeSpan(1.4f){})
    }
}