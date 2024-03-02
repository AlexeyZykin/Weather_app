package com.example.weather_app.domain.usecase

import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.domain.repository.PlaceRepository

class AddPlaceUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke(place: Place) {
        placeRepository.addPlace(place)
    }
}