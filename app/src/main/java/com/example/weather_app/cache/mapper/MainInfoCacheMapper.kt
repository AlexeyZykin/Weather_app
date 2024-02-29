package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.MainInfoCache
import com.example.weather_app.data.model.weather.MainInfoEntity

class MainInfoCacheMapper : Mapper<MainInfoCache, MainInfoEntity> {
    override fun mapFromCache(data: MainInfoCache): MainInfoEntity {
        return MainInfoEntity(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }

    override fun mapToCache(data: MainInfoEntity): MainInfoCache {
        return MainInfoCache(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }
}