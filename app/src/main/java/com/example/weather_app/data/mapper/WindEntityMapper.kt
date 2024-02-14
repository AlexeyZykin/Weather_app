package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.WindEntity
import com.example.weather_app.domain.model.Wind

class WindEntityMapper : Mapper<WindEntity, Wind> {
    override fun mapFromEntity(data: WindEntity): Wind {
        return Wind(data.speed)
    }

    override fun mapToEntity(data: Wind): WindEntity {
        return WindEntity(data.speed)
    }
}