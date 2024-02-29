package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.WeatherEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.WeatherResponse

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