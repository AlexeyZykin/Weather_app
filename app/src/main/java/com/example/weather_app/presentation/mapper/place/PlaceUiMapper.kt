package com.example.weather_app.presentation.mapper.place

import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.place.PlaceUi

class PlaceUiMapper : Mapper<PlaceUi, Place> {
    override fun mapFromUi(data: PlaceUi): Place {
        return Place(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }

    override fun mapToUi(data: Place): PlaceUi {
        return PlaceUi(
            id = data.id,
            city = data.city,
            lon = data.lon,
            lat = data.lat,
            placeIdStr = data.placeIdStr
        )
    }
}