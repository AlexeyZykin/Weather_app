package com.example.weather_app.cache.room.model

import androidx.room.ColumnInfo
import androidx.room.Embedded


data class ForecastItemCache(
    @ColumnInfo("dt") val dt: Long,
    @Embedded val mainInfo: MainInfoCache,
    @Embedded val weather: WeatherCache,
    @ColumnInfo("visibility") val visibility: Int,
    @ColumnInfo("pop") val pop: Double,
    @Embedded val clouds: CloudsCache,
    @Embedded val wind: WindCache,
    @ColumnInfo("partOfDay") val partOfDay: String,
    @ColumnInfo("dtTxt") val dtTxt: String
)
