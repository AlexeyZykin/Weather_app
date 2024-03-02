package com.example.weather_app.data.source

import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.data.model.place.PlaceEntity

interface PlaceRemoteDataSource {
    suspend fun fetchAutocompletePlaces(textInput: String): AutocompletePlaceEntity
    suspend fun fetchPlace(city: String): PlaceEntity
}