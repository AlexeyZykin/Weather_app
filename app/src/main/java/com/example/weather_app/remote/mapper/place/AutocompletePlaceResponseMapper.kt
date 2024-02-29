package com.example.weather_app.remote.mapper.place

import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.place.AutocompletePlaceResponse

class AutocompletePlaceResponseMapper(private val placeResponseMapper: PlaceResponseMapper) :
    Mapper<AutocompletePlaceResponse, AutocompletePlaceEntity> {
    override fun mapFromResponse(data: AutocompletePlaceResponse): AutocompletePlaceEntity {
        return AutocompletePlaceEntity(data.results.map { placeResponseMapper.mapFromResponse(it) })
    }

    override fun mapToResponse(data: AutocompletePlaceEntity): AutocompletePlaceResponse {
        return AutocompletePlaceResponse(data.results.map { placeResponseMapper.mapToResponse(it) })
    }
}