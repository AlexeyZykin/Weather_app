package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo

data class SysCache(
    @ColumnInfo("sunrise") val sunrise: Long,
    @ColumnInfo("sunset") val sunset: Long
)
