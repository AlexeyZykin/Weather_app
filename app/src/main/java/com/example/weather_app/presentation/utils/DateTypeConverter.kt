package com.example.weather_app.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateTypeConverter {
    private const val OUTPUT_TIME_FORMAT = "EE, d MMM H:mm"
    private const val DAY_OF_WEEK_DATE_FORMAT = "EEEE"
    private const val HOUR_DATE_FORMAT = "H:mm"
    private const val DATE_FORMAT = "d MMM"
    private const val HOURLY_FORECAST_DETAILS_DATE_FORMAT = "EE H:mm"
    //todo("change lang")
    fun convertUnixToDateString(unix: Long): String {
        val sdf = SimpleDateFormat(OUTPUT_TIME_FORMAT, Locale("en"))
        val date = Date(unix * 1000)
        return sdf.format(date)
    }

    fun convertUnixToDayOfWeek(unix: Long): String {
        val sdf = SimpleDateFormat(DAY_OF_WEEK_DATE_FORMAT, Locale("en"))
        val date = Date(unix * 1000)
        return sdf.format(date)
    }

    fun convertUnixToHour(unix: Long): String {
        val sdf = SimpleDateFormat(HOUR_DATE_FORMAT, Locale("en"))
        val date = Date(unix * 1000)
        return sdf.format(date)
    }

    fun convertUnixToDate(unix: Long): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale("en"))
        val date = Date(unix * 1000)
        return sdf.format(date)
    }

    fun convertUnixToHourlyForecastDetailsDate(unix: Long): String {
        val sdf = SimpleDateFormat(HOURLY_FORECAST_DETAILS_DATE_FORMAT, Locale("en"))
        val date = Date(unix * 1000)
        return sdf.format(date)
    }

    fun currentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale("en"))
        val time = Calendar.getInstance().time
        return sdf.format(time)
    }
}