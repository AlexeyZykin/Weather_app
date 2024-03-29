package com.example.weather_app.domain.usecase.place

import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.domain.repository.PlaceRepository

class LoadPlaceUseCase(private val placeRepository: PlaceRepository) {
    suspend fun invoke(city: String): Place {
        return placeRepository.loadPlace(city)
    }
}