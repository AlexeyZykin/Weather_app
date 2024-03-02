package com.example.weather_app.domain.usecase

import com.example.weather_app.domain.repository.PlaceRepository

class DeleteAllPlacesUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke() {
        placeRepository.deleteAllPlaces()
    }
}