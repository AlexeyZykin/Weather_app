package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.MainInfoEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.MainInfoResponse

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