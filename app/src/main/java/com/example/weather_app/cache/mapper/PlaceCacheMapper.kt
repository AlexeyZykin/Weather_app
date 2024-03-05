package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.mapper.Mapper
import com.example.weather_app.cache.room.model.PlaceCache
import com.example.weather_app.data.model.place.PlaceEntity

class PlaceCacheMapper : Mapper<PlaceCache, PlaceEntity> {
    override fun mapFromCache(data: PlaceCache): PlaceEntity {
        return PlaceEntity(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }

    override fun mapToCache(data: PlaceEntity): PlaceCache {
        return PlaceCache(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }
}