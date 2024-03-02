package com.example.weather_app.cache.source

import com.example.weather_app.cache.mapper.PlaceCacheMapper
import com.example.weather_app.cache.room.dao.PlaceDao
import com.example.weather_app.data.model.place.PlaceEntity
import com.example.weather_app.data.source.PlaceCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceCacheDataSourceImpl(
    private val placeDao: PlaceDao,
    private val placeCacheMapper: PlaceCacheMapper
) : PlaceCacheDataSource {
    override suspend fun getPlacesFromCache(): Flow<List<PlaceEntity>> {
        return placeDao.getPlaces().map { list -> list.map { placeCacheMapper.mapFromCache(it) } }
    }

    override suspend fun addPlaceToCache(place: PlaceEntity) {
        placeDao.addPlace(placeCacheMapper.mapToCache(place))
    }

    override suspend fun deletePlace(id: Int) {
        placeDao.deletePlace(id)
    }

    override suspend fun clearCache() {
        placeDao.deleteAllPlaces()
    }

    override suspend fun isCached() {
        TODO("Not yet implemented")
    }
}