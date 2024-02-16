package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.Coordinates
import com.example.weather_app.presentation.model.CoordinatesUi

class CoordinatesUiMapper : Mapper<CoordinatesUi, Coordinates>{
    override fun mapFromUi(data: CoordinatesUi): Coordinates {
        return Coordinates(data.lon, data.lat)
    }

    override fun mapToUi(data: Coordinates): CoordinatesUi {
        return CoordinatesUi(data.lon, data.lat)
    }
}