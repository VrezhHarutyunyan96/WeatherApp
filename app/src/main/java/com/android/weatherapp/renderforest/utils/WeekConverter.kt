package com.android.weatherapp.renderforest.utils

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

fun getDay(day: Int): String? {
    return day.let { getDateTime(it.toLong())?.getDisplayName(TextStyle.FULL, Locale.getDefault()) }
}

private fun getDateTime(s: Long): DayOfWeek? {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(s * 1000)
        val formattedDate = sdf.format(netDate)

        LocalDate.of(
            formattedDate.substringAfterLast("/").toInt(),
            formattedDate.substringAfter("/").take(2).toInt(),
            formattedDate.substringBefore("/").toInt()
        )
            .dayOfWeek
    } catch (e: Exception) {
        e.printStackTrace()
        DayOfWeek.MONDAY
    }
}

