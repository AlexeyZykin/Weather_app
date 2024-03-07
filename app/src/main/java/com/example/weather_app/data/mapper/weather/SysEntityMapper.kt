package com.example.weather_app.data.mapper.weather

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.weather.SysEntity
import com.example.weather_app.domain.model.weather.Sys

class SysEntityMapper : Mapper<SysEntity, Sys> {
    override fun mapFromEntity(data: SysEntity) = Sys(data.sunrise, data.sunset)
    override fun mapToEntity(data: Sys) = SysEntity(data.sunrise, data.sunset)
}