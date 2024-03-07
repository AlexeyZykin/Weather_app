package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.cache.room.model.weather.CurrentWeatherCache
import com.example.weather_app.core.Config

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM ${Config.ROOM_CURRENT_WEATHER_TABLE_NAME}")
    suspend fun getCurrentWeather(): CurrentWeatherCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrentWeather(data: CurrentWeatherCache)

    @Query("DELETE FROM ${Config.ROOM_CURRENT_WEATHER_TABLE_NAME}")
    suspend fun deleteCurrentWeather()

    @Query("SELECT (SELECT COUNT(*) FROM ${Config.ROOM_CURRENT_WEATHER_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean
}