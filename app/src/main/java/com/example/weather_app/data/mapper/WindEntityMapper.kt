package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.weather.WindEntity
import com.example.weather_app.domain.model.weather.Wind

class WindEntityMapper : Mapper<WindEntity, Wind> {
    override fun mapFromEntity(data: WindEntity) = Wind(data.speed)

    override fun mapToEntity(data: Wind) = WindEntity(data.speed)
}