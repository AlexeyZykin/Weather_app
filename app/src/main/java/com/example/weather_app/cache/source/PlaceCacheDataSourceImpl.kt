package com.example.weather_app.cache.source

import com.example.weather_app.cache.mapper.PlaceCacheMapper
import com.example.weather_app.cache.room.dao.PlaceDao
import com.example.weather_app.data.model.place.PlaceEntity
import com.example.weather_app.data.source.PlaceCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PlaceCacheDataSourceImpl(
    private val placeDao: PlaceDao,
    private val placeCacheMapper: PlaceCacheMapper
) : PlaceCacheDataSource {
    override fun getPlacesFromCache(): Flow<List<PlaceEntity>> {
        return placeDao.getPlaces().map { list -> list.map { placeCacheMapper.mapFromCache(it) } }
    }

    override suspend fun addPlaceToCache(place: PlaceEntity) {
        val cachedPlaces = placeDao.getPlaces().firstOrNull()
        val newPlace = placeCacheMapper.mapToCache(place)
        val cityIsCached = cachedPlaces?.find { it.city == newPlace.city }
        if (cityIsCached == null)
            placeDao.addPlace(newPlace)
    }

    override suspend fun loadPlaceFromCache(city: String): PlaceEntity {
        return placeCacheMapper.mapFromCache(placeDao.getPlaceByCity(city))

    }

    override suspend fun deletePlaceFromCache(id: Int) {
        placeDao.deletePlace(id)
    }

    override suspend fun deletePlacesExceptCurrent(currentCity: String) {
        placeDao.deleteAllPlacesExceptCurrent(currentCity)
    }

    override suspend fun isCached() {
        TODO("Not yet implemented")
    }
}