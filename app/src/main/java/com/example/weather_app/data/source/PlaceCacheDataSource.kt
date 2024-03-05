package com.example.weather_app.data.source

import com.example.weather_app.data.model.place.PlaceEntity
import kotlinx.coroutines.flow.Flow

interface PlaceCacheDataSource {
    fun getPlacesFromCache(): Flow<List<PlaceEntity>>
    suspend fun addPlaceToCache(place: PlaceEntity)
    suspend fun loadPlaceFromCache(city: String): PlaceEntity
    suspend fun deletePlaceFromCache(id: Int)
    suspend fun deletePlacesExceptCurrent(currentCity: String)
    suspend fun isCached()
}