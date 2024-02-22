package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.cache.room.model.ForecastWeatherCache
import com.example.weather_app.core.Constants


@Dao
interface ForecastWeatherDao {
    @Query("SELECT * FROM ${Constants.ROOM_FORECAST_WEATHER_TABLE_NAME}")
    fun getForecastWeather(): ForecastWeatherCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addForecastWeather(data: ForecastWeatherCache)

    @Query("DELETE FROM ${Constants.ROOM_FORECAST_WEATHER_TABLE_NAME}")
    fun deleteForecastWeather()

    @Query("SELECT (SELECT COUNT(*) FROM ${Constants.ROOM_FORECAST_WEATHER_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean
}