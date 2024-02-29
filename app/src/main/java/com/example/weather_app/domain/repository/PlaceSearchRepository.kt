package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.place.AutocompletePlace
import kotlinx.coroutines.flow.Flow


interface PlaceSearchRepository {
    suspend fun fetchAutocompletePlaces(textInput: String): Flow<Response<AutocompletePlace>>
}