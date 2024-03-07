package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo

data class MainInfoCache(
    @ColumnInfo("temp") val temp: Int,
    @ColumnInfo("feelsLike") val feelsLike: Int,
    @ColumnInfo("pressure") val pressure: Int,
    @ColumnInfo("humidity") val humidity: Int
)