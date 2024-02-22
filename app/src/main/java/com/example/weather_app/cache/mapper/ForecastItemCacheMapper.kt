package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.ForecastItemCache
import com.example.weather_app.data.model.ForecastItemEntity

class ForecastItemCacheMapper(
    private val mainInfoCacheMapper: MainInfoCacheMapper,
    private val weatherCacheMapper: WeatherCacheMapper,
    private val cloudsCacheMapper: CloudsCacheMapper,
    private val windCacheMapper: WindCacheMapper
) : Mapper<ForecastItemCache, ForecastItemEntity> {
    override fun mapFromCache(data: ForecastItemCache): ForecastItemEntity {
        return ForecastItemEntity(
            dt = data.dt,
            mainInfo = mainInfoCacheMapper.mapFromCache(data.mainInfo),
            weather = weatherCacheMapper.mapFromCache(data.weather),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsCacheMapper.mapFromCache(data.clouds),
            wind = windCacheMapper.mapFromCache(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }

    override fun mapToCache(data: ForecastItemEntity): ForecastItemCache {
        return ForecastItemCache(
            dt = data.dt,
            mainInfo = mainInfoCacheMapper.mapToCache(data.mainInfo),
            weather = weatherCacheMapper.mapToCache(data.weather),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsCacheMapper.mapToCache(data.clouds),
            wind = windCacheMapper.mapToCache(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }
}