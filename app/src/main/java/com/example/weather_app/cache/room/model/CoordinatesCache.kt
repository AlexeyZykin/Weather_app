package com.example.weather_app.cache.room.model

import androidx.room.ColumnInfo

data class CoordinatesCache(
    @ColumnInfo("lon") val lon: Double,
    @ColumnInfo("lat") val lat: Double
)
