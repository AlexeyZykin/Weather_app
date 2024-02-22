package com.example.weather_app.cache.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather_app.cache.room.converter.Converters
import com.example.weather_app.cache.room.dao.CurrentWeatherDao
import com.example.weather_app.cache.room.dao.ForecastWeatherDao
import com.example.weather_app.cache.room.model.CurrentWeatherCache
import com.example.weather_app.cache.room.model.ForecastWeatherCache

@Database(entities = [CurrentWeatherCache::class, ForecastWeatherCache::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getForecastWeatherDao(): ForecastWeatherDao
}