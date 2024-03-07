package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.Coordinates
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.CoordinatesUi

class CoordinatesUiMapper : Mapper<CoordinatesUi, Coordinates> {
    override fun mapFromUi(data: CoordinatesUi) = Coordinates(data.lon, data.lat)
    override fun mapToUi(data: Coordinates) = CoordinatesUi(data.lon, data.lat)
}