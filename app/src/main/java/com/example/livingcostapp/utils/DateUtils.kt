package com.example.livingcostapp.utils

import java.util.Calendar
import java.util.Date

class DateUtils {
    fun getStartEndOfCurrentMonth(): Pair<Date , Date> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH,1)
        val start = calendar.time

        calendar.add(Calendar.MONTH,1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DATE ,-1)
        val end = calendar.time

        return Pair(start, end)
    }
}