package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.domain.model.place.Place
import kotlinx.coroutines.flow.Flow


interface PlaceRepository {
    suspend fun fetchAutocompletePlaces(textInput: String): Flow<Response<AutocompletePlace>>
    suspend fun fetchPlace(city: String): Flow<Response<Place>>
    suspend fun loadPlace(city: String): Place
    suspend fun fetchAllPlaces(): Flow<Response<List<Place>>>
    suspend fun addPlace(place: Place)
    suspend fun deleteAllPlaces(currentPlace: String)
    suspend fun deletePlace(id: Int)
    suspend fun updatePlace(place: Place)
}