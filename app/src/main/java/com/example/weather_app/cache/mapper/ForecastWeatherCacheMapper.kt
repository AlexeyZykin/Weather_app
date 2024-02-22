package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.ForecastWeatherCache
import com.example.weather_app.data.model.ForecastWeatherEntity

class ForecastWeatherCacheMapper(
    private val forecastItemCacheMapper: ForecastItemCacheMapper,
    private val cityCacheMapper: CityCacheMapper
) : Mapper<ForecastWeatherCache, ForecastWeatherEntity> {
    override fun mapFromCache(data: ForecastWeatherCache): ForecastWeatherEntity {
        return ForecastWeatherEntity(
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemCacheMapper.mapFromCache(it) },
            city = cityCacheMapper.mapFromCache(data.city)
        )
    }

    override fun mapToCache(data: ForecastWeatherEntity): ForecastWeatherCache {
        return ForecastWeatherCache(
            id = null,
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemCacheMapper.mapToCache(it) },
            city = cityCacheMapper.mapToCache(data.city)
        )
    }
}