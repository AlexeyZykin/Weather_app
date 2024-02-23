package com.example.weather_app.cache.room.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weather_app.core.Constants


@Entity(tableName = Constants.ROOM_FORECAST_WEATHER_TABLE_NAME)
data class ForecastWeatherCache(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("cnt") val cnt: Int,
    @TypeConverters @ColumnInfo("forecastList") val forecastList: List<ForecastItemCache>,
    @Embedded val city: CityCache
)