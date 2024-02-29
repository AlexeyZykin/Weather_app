package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.domain.repository.PlaceSearchRepository
import kotlinx.coroutines.flow.Flow

class FetchAutocompletePlacesUseCase(private val placeSearchRepository: PlaceSearchRepository) {
    suspend fun invoke(inputText: String): Flow<Response<AutocompletePlace>> {
        return placeSearchRepository.fetchAutocompletePlaces(inputText)
    }
}