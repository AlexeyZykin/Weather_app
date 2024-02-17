package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.MainInfoEntity
import com.example.weather_app.domain.model.MainInfo

class MainInfoEntityMapper : Mapper<MainInfoEntity, MainInfo> {
    override fun mapFromEntity(data: MainInfoEntity): MainInfo {
        return MainInfo(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }

    override fun mapToEntity(data: MainInfo): MainInfoEntity {
        return MainInfoEntity(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }
}