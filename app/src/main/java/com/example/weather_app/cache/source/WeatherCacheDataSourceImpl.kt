package com.example.weather_app.cache.source

import com.example.weather_app.cache.mapper.CurrentWeatherCacheMapper
import com.example.weather_app.cache.mapper.ForecastWeatherCacheMapper
import com.example.weather_app.cache.room.dao.CurrentWeatherDao
import com.example.weather_app.cache.room.dao.ForecastWeatherDao
import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.data.model.ForecastWeatherEntity
import com.example.weather_app.data.source.WeatherCacheDataSource

class WeatherCacheDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao,
    private val currentWeatherCacheMapper: CurrentWeatherCacheMapper,
    private val forecastWeatherCacheMapper: ForecastWeatherCacheMapper
    ) : WeatherCacheDataSource{
    override suspend fun addCurrentWeatherToCache(data: CurrentWeatherEntity) {
        currentWeatherDao.addCurrentWeather(currentWeatherCacheMapper.mapToCache(data))
    }

    override suspend fun getCurrentWeatherFromCache(): CurrentWeatherEntity {
        return currentWeatherCacheMapper.mapFromCache(currentWeatherDao.getLastCurrentWeather())
    }

    override suspend fun clearCacheCurrentWeather() {
        currentWeatherDao.deleteCurrentWeather()
    }

    override suspend fun isCachedCurrentWeather(): Boolean {
        return currentWeatherDao.isNotEmpty()
    }

    override suspend fun addForecastWeatherToCache(data: ForecastWeatherEntity) {
        forecastWeatherDao.addForecastWeather(forecastWeatherCacheMapper.mapToCache(data))
    }

    override suspend fun getForecastWeatherFromCache(): ForecastWeatherEntity {
        return forecastWeatherCacheMapper.mapFromCache(forecastWeatherDao.getForecastWeather())
    }

    override suspend fun clearCacheForecastWeather() {
        forecastWeatherDao.deleteForecastWeather()
    }

    override suspend fun isCachedForecast(): Boolean {
        return forecastWeatherDao.isNotEmpty()
    }
}