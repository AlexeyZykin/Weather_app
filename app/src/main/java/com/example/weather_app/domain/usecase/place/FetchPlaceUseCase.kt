package com.example.weather_app.domain.usecase.place

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow

class FetchPlaceUseCase(private val placeRepository: PlaceRepository) {
    fun invoke(city: String): Flow<Response<Place>> {
        return placeRepository.fetchPlace(city)
    }
}