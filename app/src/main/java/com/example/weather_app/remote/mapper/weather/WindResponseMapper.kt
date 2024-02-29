package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.WindEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.WindResponse

class WindResponseMapper : Mapper<WindResponse, WindEntity> {
    override fun mapFromResponse(data: WindResponse) = WindEntity(data.speed.toInt())
    override fun mapToResponse(data: WindEntity) = WindResponse(data.speed.toFloat())
}