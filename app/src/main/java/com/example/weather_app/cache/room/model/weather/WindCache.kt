package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo

data class WindCache(@ColumnInfo("wind_speed") val speed: Int)
