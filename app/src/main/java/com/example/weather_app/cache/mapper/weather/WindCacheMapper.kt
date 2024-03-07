package com.example.weather_app.cache.mapper.weather

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.weather.WindCache
import com.example.weather_app.data.model.weather.WindEntity

class WindCacheMapper : Mapper<WindCache, WindEntity> {
    override fun mapFromCache(data: WindCache) = WindEntity(data.speed)

    override fun mapToCache(data: WindEntity) = WindCache(data.speed)
}