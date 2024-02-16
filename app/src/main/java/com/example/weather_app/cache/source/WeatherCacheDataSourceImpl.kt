package com.example.weather_app.cache.source

import com.example.weather_app.cache.mapper.CurrentWeatherCacheMapper
import com.example.weather_app.cache.room.dao.CurrentWeatherDao
import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.data.source.WeatherCacheDataSource

class WeatherCacheDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val currentWeatherCacheMapper: CurrentWeatherCacheMapper
    ) : WeatherCacheDataSource{
    override suspend fun addCurrentWeatherToCache(data: CurrentWeatherEntity) {
        currentWeatherDao.addCurrentWeather(currentWeatherCacheMapper.mapToCache(data))
    }

    override suspend fun getCurrentWeatherFromCache(): CurrentWeatherEntity {
        return currentWeatherCacheMapper.mapFromCache(currentWeatherDao.getLastCurrentWeather())
    }

    override suspend fun clearCache() {
        currentWeatherDao.deleteCurrentWeather()
    }

    override suspend fun isCached(): Boolean {
        return currentWeatherDao.isNotEmpty()
    }


}