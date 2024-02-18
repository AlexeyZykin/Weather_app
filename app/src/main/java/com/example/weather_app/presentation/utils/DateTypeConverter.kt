package com.example.weather_app.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateTypeConverter {
    private const val OUTPUT_TIME_FORMAT = "EE, d MMM H:mm"
    //todo("change lang")
    fun convertUnixToDateString(unix: Long): String {
        val sdf = SimpleDateFormat(OUTPUT_TIME_FORMAT, Locale("en"))
        val date = java.util.Date(unix * 1000)
        return sdf.format(date)
    }
}