package com.example.weather_app.cache.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather_app.cache.room.dao.CurrentWeatherDao
import com.example.weather_app.cache.room.model.CurrentWeatherCache

@Database(entities = [CurrentWeatherCache::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
}