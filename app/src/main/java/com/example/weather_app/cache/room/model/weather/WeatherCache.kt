package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo

data class WeatherCache(
    @ColumnInfo("weather_id") val id: Int,
    @ColumnInfo("weather_main") val main: String,
    @ColumnInfo("weather_description") val description: String,
    @ColumnInfo("weather_icon") val icon: String
)
