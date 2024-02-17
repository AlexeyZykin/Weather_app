package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.WeatherEntity
import com.example.weather_app.remote.model.WeatherResponse

class WeatherResponseMapper : Mapper<WeatherResponse, WeatherEntity> {
    override fun mapFromResponse(data: WeatherResponse) =
        WeatherEntity(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )

    override fun mapToResponse(data: WeatherEntity) =
        WeatherResponse(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )
}