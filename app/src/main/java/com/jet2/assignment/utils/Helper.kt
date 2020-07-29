package com.jet2.assignment.utils

import org.joda.time.*
import java.util.*


fun getFormattedNumber(number: Int, suffix: String): String {
    var formattedValue = ""
    try {
        val crore = 10000000
        val lacs = 100000
        val thousand = 1000
        var value = 0.0f
        var unit = ""
        if (number >= crore) {
            value = (number.toFloat() / crore)
            unit = "Cr"
        } else if (number >= lacs) {
            value = (number.toFloat() / lacs)
            unit = "L"
        } else if (number >= thousand) {
            value = (number.toFloat() / thousand)
            unit = "K"
        } else{
            value = number.toFloat()
        }

        formattedValue = "${String.format(Locale.US, "%.1f", value)}$unit $suffix"
    } catch (e: Exception) {
        return formattedValue
    }
    return formattedValue
}


fun getFormattedDayValue(articleDate: String?): String {
    val _now = DateTime.now()
    val _articleDate = DateTime.parse(articleDate)

    val days = Days.daysBetween(_articleDate, _now).days
    val minutes = Minutes.minutesBetween(_articleDate, _now).minutes
    val hours = Hours.hoursBetween(_articleDate, _now).hours
    val months = Months.monthsBetween(_articleDate, _now).months
    val years = Years.yearsBetween(_articleDate, _now).years

    val timeDiff = when{
            years > 0 -> "$years yr"
            months > 0 -> "$months month"
            days > 0 -> "$days day"
            hours > 0 -> "$hours hr"
            else -> "$minutes min"
        }


    return timeDiff
}