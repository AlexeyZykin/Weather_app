package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo
import androidx.room.Embedded


data class CityCache(
    @ColumnInfo("city_id") val id: Int,
    @ColumnInfo("city_name") val name: String,
    @Embedded val coord: CoordinatesCache,
    @ColumnInfo("country") val country: String,
    @ColumnInfo("city_population") val population: Int,
    @ColumnInfo("sunrise") val sunrise: Long,
    @ColumnInfo("sunset") val sunset: Long
)