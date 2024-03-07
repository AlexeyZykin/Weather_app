package com.example.weather_app.cache.source

import com.example.weather_app.cache.mapper.weather.CurrentWeatherCacheMapper
import com.example.weather_app.cache.mapper.weather.ForecastItemCacheMapper
import com.example.weather_app.cache.mapper.weather.ForecastWeatherCacheMapper
import com.example.weather_app.cache.room.dao.CurrentWeatherDao
import com.example.weather_app.cache.room.dao.ForecastWeatherDao
import com.example.weather_app.data.model.weather.CurrentWeatherEntity
import com.example.weather_app.data.model.weather.ForecastItemEntity
import com.example.weather_app.data.model.weather.ForecastWeatherEntity
import com.example.weather_app.data.source.WeatherCacheDataSource

class WeatherCacheDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao,
    private val currentWeatherCacheMapper: CurrentWeatherCacheMapper,
    private val forecastWeatherCacheMapper: ForecastWeatherCacheMapper,
    private val forecastItemCacheMapper: ForecastItemCacheMapper
) : WeatherCacheDataSource {
    override suspend fun addCurrentWeatherToCache(data: CurrentWeatherEntity) {
        currentWeatherDao.addCurrentWeather(currentWeatherCacheMapper.mapToCache(data))
    }

    override suspend fun getCurrentWeather(): CurrentWeatherEntity {
        return currentWeatherCacheMapper.mapFromCache(currentWeatherDao.getCurrentWeather())
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

    override suspend fun getForecastByTime(dt: Long): ForecastItemEntity {
        return forecastItemCacheMapper.mapFromCache(
            forecastWeatherDao.getForecastWeather().forecastList.first { it.dt == dt }
        )
    }

    override suspend fun getForecastByDay(dtTxt: String): List<ForecastItemEntity> {
        return forecastWeatherDao.getForecastWeather().forecastList.filter {
            it.dtTxt.substring(0, 10) == dtTxt
        }.map {
            forecastItemCacheMapper.mapFromCache(it)
        }
    }
}