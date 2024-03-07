package com.example.weather_app.cache.mapper.weather

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.weather.WeatherCache
import com.example.weather_app.data.model.weather.WeatherEntity

class WeatherCacheMapper : Mapper<WeatherCache, WeatherEntity> {
    override fun mapFromCache(data: WeatherCache): WeatherEntity {
        return WeatherEntity(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )
    }

    override fun mapToCache(data: WeatherEntity): WeatherCache {
        return WeatherCache(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )
    }
}