package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.cache.room.model.weather.ForecastWeatherCache
import com.example.weather_app.core.Config


@Dao
interface ForecastWeatherDao {
    @Query("SELECT * FROM ${Config.ROOM_FORECAST_WEATHER_TABLE_NAME}")
    suspend fun getForecastWeather(): ForecastWeatherCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addForecastWeather(data: ForecastWeatherCache)

    @Query("DELETE FROM ${Config.ROOM_FORECAST_WEATHER_TABLE_NAME}")
    suspend fun deleteForecastWeather()

    @Query("SELECT (SELECT COUNT(*) FROM ${Config.ROOM_FORECAST_WEATHER_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean
}