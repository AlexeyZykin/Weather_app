package com.example.weather_app.remote.source

import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.data.model.place.PlaceEntity
import com.example.weather_app.data.source.PlaceRemoteDataSource
import com.example.weather_app.remote.api.PlaceService
import com.example.weather_app.remote.mapper.place.AutocompletePlaceResponseMapper
import com.example.weather_app.remote.mapper.place.PlaceResponseMapper

class PlaceRemoteDataSourceImpl(
    private val placeService: PlaceService,
    private val autocompletePlaceResponseMapper: AutocompletePlaceResponseMapper,
    private val placeResponseMapper: PlaceResponseMapper
    ) : PlaceRemoteDataSource {
    override suspend fun fetchAutocompletePlaces(textInput: String): AutocompletePlaceEntity {
        return autocompletePlaceResponseMapper.mapFromResponse(
            placeService.fetchAutoCompletePlaces(inputText = textInput)
        )
    }

    override suspend fun fetchPlace(city: String): PlaceEntity {
        return placeResponseMapper.mapFromResponse(
            placeService.fetchPlace(cityName = city).results[0]
        )
    }
}