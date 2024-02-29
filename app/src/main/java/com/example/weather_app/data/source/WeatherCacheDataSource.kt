package com.example.weather_app.data.source

import com.example.weather_app.data.model.weather.CurrentWeatherEntity
import com.example.weather_app.data.model.weather.ForecastItemEntity
import com.example.weather_app.data.model.weather.ForecastWeatherEntity

interface WeatherCacheDataSource {
    suspend fun addCurrentWeatherToCache(data: CurrentWeatherEntity)
    suspend fun getCurrentWeatherFromCache(): CurrentWeatherEntity
    suspend fun clearCacheCurrentWeather()
    suspend fun isCachedCurrentWeather(): Boolean
    suspend fun addForecastWeatherToCache(data: ForecastWeatherEntity)
    suspend fun getForecastWeatherFromCache(): ForecastWeatherEntity
    suspend fun clearCacheForecastWeather()
    suspend fun isCachedForecast(): Boolean
    suspend fun getForecastByTime(dt: Long): ForecastItemEntity
    suspend fun getForecastByDay(dtTxt: String): List<ForecastItemEntity>
}