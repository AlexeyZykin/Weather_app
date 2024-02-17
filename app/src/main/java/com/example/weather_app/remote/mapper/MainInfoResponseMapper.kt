package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.MainInfoEntity
import com.example.weather_app.remote.model.MainInfoResponse

class MainInfoResponseMapper : Mapper<MainInfoResponse, MainInfoEntity> {
    override fun mapFromResponse(data: MainInfoResponse): MainInfoEntity {
        return MainInfoEntity(
            temp = data.temp.toInt(),
            feelsLike = data.feels_like.toInt(),
            pressure = data.pressure,
            humidity = data.humidity
        )
    }

    override fun mapToResponse(data: MainInfoEntity): MainInfoResponse {
        return MainInfoResponse(
            temp = data.temp.toFloat(),
            feels_like = data.feelsLike.toFloat(),
            pressure = data.pressure,
            humidity = data.humidity
        )
    }
}