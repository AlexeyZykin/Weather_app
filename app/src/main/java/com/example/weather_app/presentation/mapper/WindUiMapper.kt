package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.Wind
import com.example.weather_app.presentation.model.WindUi

class WindUiMapper : Mapper<WindUi, Wind> {
    override fun mapFromUi(data: WindUi) = Wind(data.speed)
    override fun mapToUi(data: Wind) = WindUi(data.speed)
}