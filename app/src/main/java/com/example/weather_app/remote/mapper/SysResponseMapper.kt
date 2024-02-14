package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.SysEntity
import com.example.weather_app.remote.model.SysResponse

class SysResponseMapper : Mapper<SysResponse, SysEntity> {
    override fun mapFromResponse(data: SysResponse): SysEntity {
        return SysEntity(
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

    override fun mapToResponse(data: SysEntity): SysResponse {
        return SysResponse(
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

}