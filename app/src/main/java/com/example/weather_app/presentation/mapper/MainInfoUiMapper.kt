package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.MainInfo
import com.example.weather_app.presentation.model.MainInfoUi

class MainInfoUiMapper : Mapper<MainInfoUi, MainInfo> {
    override fun mapFromUi(data: MainInfoUi): MainInfo {
        return MainInfo(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }

    override fun mapToUi(data: MainInfo): MainInfoUi {
        return MainInfoUi(
            temp = data.temp,
            feelsLike = data.feelsLike,
            pressure = data.pressure,
            humidity = data.humidity
        )
    }
}