package com.example.free_pre_android.utils

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

//생리 주기가 아닌 부분 데코
class MinMaxDecorator(min: CalendarDay, max:CalendarDay): DayViewDecorator {
    val maxDay = max
    val minDay = min
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return (day?.month == maxDay.month && day.day > maxDay.day)
                || (day?.month == minDay.month && day.day < minDay.day)
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(Color.parseColor("#FDE3F4")){})     //생리주기 아닌 부분 날짜 색상
        //view?.setDaysDisabled(false)      //날짜 선택 가능, true로 하면 날짜 선택 불가능임
    }
}