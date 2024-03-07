package com.example.weather_app.cache.mapper.weather

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.weather.ForecastWeatherCache
import com.example.weather_app.data.model.weather.ForecastWeatherEntity

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