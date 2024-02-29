package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.CoordinatesCache
import com.example.weather_app.data.model.weather.CoordinatesEntity

class CoordinatesCacheMapper : Mapper<CoordinatesCache, CoordinatesEntity> {
    override fun mapFromCache(data: CoordinatesCache) = CoordinatesEntity(data.lon, data.lat)

    override fun mapToCache(data: CoordinatesEntity) = CoordinatesCache(data.lon, data.lat)
}