package com.example.weather_app.cache.room.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather_app.core.Constants

@Entity(tableName = Constants.ROOM_CURRENT_WEATHER_TABLE_NAME)
data class CurrentWeatherCache(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded
    val coord: CoordinatesCache,
    @Embedded
    val weather: WeatherCache,
    @Embedded
    val main: MainInfoCache,
    val visibility: Int,
    @Embedded
    val wind: WindCache,
    @Embedded
    val clouds: CloudsCache,
    val dt: Long,
    @ColumnInfo("city_name")
    val name: String,
    @Embedded
    val sys: SysCache
)