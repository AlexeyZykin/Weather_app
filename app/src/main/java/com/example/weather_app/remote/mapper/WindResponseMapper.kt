package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.WindEntity
import com.example.weather_app.remote.model.WindResponse

class WindResponseMapper : Mapper<WindResponse, WindEntity> {
    override fun mapFromResponse(data: WindResponse): WindEntity {
        return WindEntity(
            data.speed.toInt()
        )
    }

    override fun mapToResponse(data: WindEntity): WindResponse {
        return WindResponse(
            data.speed.toFloat()
        )
    }
}