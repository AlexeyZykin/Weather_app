package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.CoordinatesEntity
import com.example.weather_app.domain.model.Coordinates

class CoordinatesEntityMapper : Mapper<CoordinatesEntity, Coordinates> {
    override fun mapFromEntity(data: CoordinatesEntity): Coordinates {
        return Coordinates(
            lon = data.lon,
            lat = data.lat
        )
    }

    override fun mapToEntity(data: Coordinates): CoordinatesEntity {
        return CoordinatesEntity(
            lon = data.lon,
            lat = data.lat
        )
    }

}