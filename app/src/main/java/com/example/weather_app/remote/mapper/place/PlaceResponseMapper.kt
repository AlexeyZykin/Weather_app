package com.example.weather_app.remote.mapper.place

import com.example.weather_app.data.model.place.PlaceEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.place.PlaceResponse

class PlaceResponseMapper : Mapper<PlaceResponse, PlaceEntity> {
    override fun mapFromResponse(data: PlaceResponse): PlaceEntity {
        return PlaceEntity(
            id = null,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.place_id
        )
    }

    override fun mapToResponse(data: PlaceEntity): PlaceResponse {
        return PlaceResponse(
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            place_id = data.placeIdStr
        )
    }
}