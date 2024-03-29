package com.example.weather_app.domain.usecase.place

import com.example.weather_app.domain.repository.PlaceRepository

class DeletePlaceUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke(id: Int) {
        placeRepository.deletePlace(id)
    }
}