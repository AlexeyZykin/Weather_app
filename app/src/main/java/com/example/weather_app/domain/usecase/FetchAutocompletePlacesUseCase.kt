package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow

class FetchAutocompletePlacesUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke(inputText: String): Flow<Response<AutocompletePlace>> {
        return placeRepository.fetchAutocompletePlaces(inputText)
    }
}