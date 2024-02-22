package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.CityCache
import com.example.weather_app.data.model.CityEntity

class CityCacheMapper(private val coordinatesCacheMapper: CoordinatesCacheMapper) : Mapper<CityCache, CityEntity>{
    override fun mapFromCache(data: CityCache): CityEntity {
        return CityEntity(
            id = data.id,
            name = data.name,
            coord = coordinatesCacheMapper.mapFromCache(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunrise
        )
    }

    override fun mapToCache(data: CityEntity): CityCache {
        return CityCache(
            id = data.id,
            name = data.name,
            coord = coordinatesCacheMapper.mapToCache(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunrise
        )
    }

}
