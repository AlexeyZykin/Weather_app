package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.SysEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.SysResponse

class SysResponseMapper : Mapper<SysResponse, SysEntity> {
    override fun mapFromResponse(data: SysResponse) =
        SysEntity(sunrise = data.sunrise, sunset = data.sunset)

    override fun mapToResponse(data: SysEntity) =
        SysResponse(sunrise = data.sunrise, sunset = data.sunset)
}