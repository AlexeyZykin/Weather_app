package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.SysEntity
import com.example.weather_app.domain.model.Sys

class SysEntityMapper : Mapper<SysEntity, Sys> {
    override fun mapFromEntity(data: SysEntity) = Sys(data.sunrise, data.sunset)
    override fun mapToEntity(data: Sys) = SysEntity(data.sunrise, data.sunset)
}