package com.example.weather_app.remote.source

import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.data.source.PlaceSearchRemoteDataSource
import com.example.weather_app.remote.api.PlaceService
import com.example.weather_app.remote.mapper.place.AutocompletePlaceResponseMapper

class PlaceSearchRemoteDataSourceImpl(
    private val placeService: PlaceService,
    private val autocompletePlaceResponseMapper: AutocompletePlaceResponseMapper
    ) : PlaceSearchRemoteDataSource {
    override suspend fun fetchAutocompletePlaces(textInput: String): AutocompletePlaceEntity {
        return autocompletePlaceResponseMapper.mapFromResponse(
            placeService.fetchAutoCompletePlaces(inputText = textInput)
        )
    }
}