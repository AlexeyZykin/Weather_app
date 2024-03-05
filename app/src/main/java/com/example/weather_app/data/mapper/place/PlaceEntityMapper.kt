package com.example.weather_app.data.mapper.place

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.place.PlaceEntity
import com.example.weather_app.domain.model.place.Place

class PlaceEntityMapper : Mapper<PlaceEntity, Place> {
    override fun mapFromEntity(data: PlaceEntity): Place {
        return Place(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }

    override fun mapToEntity(data: Place): PlaceEntity {
        return PlaceEntity(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }
}