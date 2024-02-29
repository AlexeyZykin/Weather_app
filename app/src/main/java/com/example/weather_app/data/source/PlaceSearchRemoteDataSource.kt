package com.example.weather_app.data.source

import com.example.weather_app.data.model.place.AutocompletePlaceEntity

interface PlaceSearchRemoteDataSource {
    suspend fun fetchAutocompletePlaces(textInput: String): AutocompletePlaceEntity
}