package com.example.weather_app.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateTypeConverter {
    const val OUTPUT_TIME_FORMAT = "EE, d MMM H:mm"
    const val DAY_OF_WEEK_DATE_FORMAT = "EEEE"
    const val HOUR_DATE_FORMAT = "H:mm"
    const val DATE_FORMAT = "d MMM"
    const val HOURLY_FORECAST_DETAILS_DATE_FORMAT = "EE H:mm"
    const val DAILY_FORECAST_DETAILS_DATE_FORMAT = "EEEE d"
    const val DAILY_FORECAST_DETAILS_REQUEST_DATE = "y-MM-dd"

    //todo("change lang")
    fun convertUnixToDateString(dtUnix: Long, dateFormat: String): String {
        val sdf = SimpleDateFormat(dateFormat, Locale("en"))
        val date = Date(dtUnix * 1000)
        return sdf.format(date)
    }

    fun currentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale("en"))
        val time = Calendar.getInstance().time
        return sdf.format(time)
    }
}