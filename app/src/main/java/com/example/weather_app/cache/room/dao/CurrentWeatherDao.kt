package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weather_app.cache.room.model.CurrentWeatherCache
import com.example.weather_app.core.Constants

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM ${Constants.ROOM_CURRENT_WEATHER_TABLE_NAME}")
    fun getLastCurrentWeather(): CurrentWeatherCache

    @Insert
    fun addCurrentWeather(data: CurrentWeatherCache)

    @Query("DELETE FROM ${Constants.ROOM_CURRENT_WEATHER_TABLE_NAME}")
    fun deleteCurrentWeather()

    @Query("SELECT (SELECT COUNT(*) FROM ${Constants.ROOM_CURRENT_WEATHER_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean
}