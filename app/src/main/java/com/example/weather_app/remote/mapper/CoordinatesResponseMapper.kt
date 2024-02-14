package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.CoordinatesEntity
import com.example.weather_app.remote.model.CoordinatesResponse

class CoordinatesResponseMapper : Mapper<CoordinatesResponse, CoordinatesEntity> {
    override fun mapFromResponse(data: CoordinatesResponse): CoordinatesEntity {
        return CoordinatesEntity(
            lon = data.lon,
            lat = data.lat
        )
    }

    override fun mapToResponse(data: CoordinatesEntity): CoordinatesResponse {
        return CoordinatesResponse(
            lon = data.lon,
            lat = data.lat
        )
    }
}