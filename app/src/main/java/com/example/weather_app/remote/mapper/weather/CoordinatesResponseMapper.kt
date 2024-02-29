package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.CoordinatesEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.CoordinatesResponse

class CoordinatesResponseMapper : Mapper<CoordinatesResponse, CoordinatesEntity> {
    override fun mapFromResponse(data: CoordinatesResponse) =
        CoordinatesEntity(lon = data.lon, lat = data.lat)

    override fun mapToResponse(data: CoordinatesEntity) =
        CoordinatesResponse(lon = data.lon, lat = data.lat)
}