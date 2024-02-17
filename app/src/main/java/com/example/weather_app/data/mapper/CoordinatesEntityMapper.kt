package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.CoordinatesEntity
import com.example.weather_app.domain.model.Coordinates

class CoordinatesEntityMapper : Mapper<CoordinatesEntity, Coordinates> {
    override fun mapFromEntity(data: CoordinatesEntity) =
        Coordinates(lon = data.lon, lat = data.lat)

    override fun mapToEntity(data: Coordinates) =
        CoordinatesEntity(lon = data.lon, lat = data.lat)
}