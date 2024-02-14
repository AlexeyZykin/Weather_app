package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.SysEntity
import com.example.weather_app.domain.model.Sys

class SysEntityMapper : Mapper<SysEntity, Sys> {
    override fun mapFromEntity(data: SysEntity): Sys {
        return Sys(
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

    override fun mapToEntity(data: Sys): SysEntity {
        return SysEntity(
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }
}