package com.example.weather_app.presentation.mapper.place

import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.place.AutocompletePlaceUi

class AutocompletePlaceUiMapper(private val placeUiMapper: PlaceUiMapper) :
    Mapper<AutocompletePlaceUi, AutocompletePlace> {
    override fun mapFromUi(data: AutocompletePlaceUi): AutocompletePlace {
        return AutocompletePlace(
            data.results.map { placeUiMapper.mapFromUi(it) }
        )
    }

    override fun mapToUi(data: AutocompletePlace): AutocompletePlaceUi {
        return AutocompletePlaceUi(
            data.results.map { placeUiMapper.mapToUi(it) }
        )
    }
}