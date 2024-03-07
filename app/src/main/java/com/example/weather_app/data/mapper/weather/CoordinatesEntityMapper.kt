package com.example.weather_app.data.mapper.weather

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.weather.CoordinatesEntity
import com.example.weather_app.domain.model.weather.Coordinates

class CoordinatesEntityMapper : Mapper<CoordinatesEntity, Coordinates> {
    override fun mapFromEntity(data: CoordinatesEntity) =
        Coordinates(lon = data.lon, lat = data.lat)

    override fun mapToEntity(data: Coordinates) =
        CoordinatesEntity(lon = data.lon, lat = data.lat)
}