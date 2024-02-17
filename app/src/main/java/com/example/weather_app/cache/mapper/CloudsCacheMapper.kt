package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.CloudsCache
import com.example.weather_app.data.model.CloudsEntity

class CloudsCacheMapper : Mapper<CloudsCache, CloudsEntity> {
    override fun mapFromCache(data: CloudsCache) = CloudsEntity(data.all)

    override fun mapToCache(data: CloudsEntity) = CloudsCache(data.all)
}