package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.WindCache
import com.example.weather_app.data.model.WindEntity

class WindCacheMapper : Mapper<WindCache, WindEntity> {
    override fun mapFromCache(data: WindCache): WindEntity {
        return WindEntity(data.speed)
    }

    override fun mapToCache(data: WindEntity): WindCache {
        return WindCache(data.speed)
    }
}