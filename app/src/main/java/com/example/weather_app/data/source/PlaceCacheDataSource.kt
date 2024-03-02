package com.example.weather_app.data.source

import com.example.weather_app.data.model.place.PlaceEntity
import kotlinx.coroutines.flow.Flow

interface PlaceCacheDataSource {
    suspend fun getPlacesFromCache(): Flow<List<PlaceEntity>>
    suspend fun addPlaceToCache(place: PlaceEntity)
    suspend fun deletePlace(id: Int)
    suspend fun clearCache()
    suspend fun isCached()
}