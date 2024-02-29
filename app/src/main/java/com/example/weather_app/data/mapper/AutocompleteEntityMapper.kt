package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.domain.model.place.AutocompletePlace

class AutocompleteEntityMapper(private val placeEntityMapper: PlaceEntityMapper) : Mapper<AutocompletePlaceEntity, AutocompletePlace> {
    override fun mapFromEntity(data: AutocompletePlaceEntity): AutocompletePlace {
        return AutocompletePlace(data.results.map { placeEntityMapper.mapFromEntity(it) })
    }

    override fun mapToEntity(data: AutocompletePlace): AutocompletePlaceEntity {
        return AutocompletePlaceEntity(data.results.map { placeEntityMapper.mapToEntity(it) })
    }
}