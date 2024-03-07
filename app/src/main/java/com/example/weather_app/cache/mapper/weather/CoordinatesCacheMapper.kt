package com.example.weather_app.cache.mapper.weather

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.weather.CoordinatesCache
import com.example.weather_app.data.model.weather.CoordinatesEntity

class CoordinatesCacheMapper : Mapper<CoordinatesCache, CoordinatesEntity> {
    override fun mapFromCache(data: CoordinatesCache) = CoordinatesEntity(data.lon, data.lat)

    override fun mapToCache(data: CoordinatesEntity) = CoordinatesCache(data.lon, data.lat)
}