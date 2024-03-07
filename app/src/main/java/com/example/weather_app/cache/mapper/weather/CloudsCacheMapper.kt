package com.example.weather_app.cache.mapper.weather

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.weather.CloudsCache
import com.example.weather_app.data.model.weather.CloudsEntity

class CloudsCacheMapper : Mapper<CloudsCache, CloudsEntity> {
    override fun mapFromCache(data: CloudsCache) = CloudsEntity(data.all)

    override fun mapToCache(data: CloudsEntity) = CloudsCache(data.all)
}