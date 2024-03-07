package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.Wind
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.WindUi

class WindUiMapper : Mapper<WindUi, Wind> {
    override fun mapFromUi(data: WindUi) = Wind(data.speed)
    override fun mapToUi(data: Wind) = WindUi(data.speed)
}