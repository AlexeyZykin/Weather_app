package com.example.weather_app.domain.usecase

import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.domain.repository.PlaceRepository

class UpdatePlaceUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke(place: Place) {
        placeRepository.updatePlace(place)
    }
}