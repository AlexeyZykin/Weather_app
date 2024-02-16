package com.example.weather_app.data.source

import com.example.weather_app.data.model.CurrentWeatherEntity

interface WeatherCacheDataSource {
    suspend fun addCurrentWeatherToCache(data: CurrentWeatherEntity)
    suspend fun getCurrentWeatherFromCache(): CurrentWeatherEntity
    suspend fun clearCache()
    suspend fun isCached(): Boolean
}