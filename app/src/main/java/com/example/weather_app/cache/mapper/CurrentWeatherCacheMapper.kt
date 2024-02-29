package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.CurrentWeatherCache
import com.example.weather_app.data.model.weather.CurrentWeatherEntity

class CurrentWeatherCacheMapper(
    private val coordinatesCacheMapper: CoordinatesCacheMapper,
    private val weatherCacheMapper: WeatherCacheMapper,
    private val mainInfoCacheMapper: MainInfoCacheMapper,
    private val windCacheMapper: WindCacheMapper,
    private val cloudsCacheMapper: CloudsCacheMapper,
    private val sysCacheMapper: SysCacheMapper
) : Mapper<CurrentWeatherCache, CurrentWeatherEntity> {
    override fun mapFromCache(data: CurrentWeatherCache): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            id = data.id,
            coord = coordinatesCacheMapper.mapFromCache(data.coord),
            weather = weatherCacheMapper.mapFromCache(data.weather),
            main = mainInfoCacheMapper.mapFromCache(data.main),
            visibility = data.visibility,
            wind = windCacheMapper.mapFromCache(data.wind),
            clouds = cloudsCacheMapper.mapFromCache(data.clouds),
            dt = data.dt,
            name = data.name,
            sys = sysCacheMapper.mapFromCache(data.sys)
        )
    }

    override fun mapToCache(data: CurrentWeatherEntity): CurrentWeatherCache {
        return CurrentWeatherCache(
            id = data.id,
            coord = coordinatesCacheMapper.mapToCache(data.coord),
            weather = weatherCacheMapper.mapToCache(data.weather),
            main = mainInfoCacheMapper.mapToCache(data.main),
            visibility = data.visibility,
            wind = windCacheMapper.mapToCache(data.wind),
            clouds = cloudsCacheMapper.mapToCache(data.clouds),
            dt = data.dt,
            name = data.name,
            sys = sysCacheMapper.mapToCache(data.sys)
        )
    }
}