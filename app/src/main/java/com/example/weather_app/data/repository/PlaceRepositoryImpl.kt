package com.example.weather_app.data.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.mapper.place.AutocompletePlaceEntityMapper
import com.example.weather_app.data.mapper.place.PlaceEntityMapper
import com.example.weather_app.data.source.PlaceCacheDataSource
import com.example.weather_app.data.source.PlaceRemoteDataSource
import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.domain.model.place.Place
import com.example.weather_app.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion

class PlaceRepositoryImpl(
    private val placeCacheDataSource: PlaceCacheDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val autocompletePlaceEntityMapper: AutocompletePlaceEntityMapper,
    private val placeEntityMapper: PlaceEntityMapper
) :
    PlaceRepository {
    override suspend fun fetchAutocompletePlaces(textInput: String): Flow<Response<AutocompletePlace>> =
        flow {
            emit(Response.Loading(true))

            val response = try {
                placeRemoteDataSource.fetchAutocompletePlaces(textInput)
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "Error"))
                null
            }

            if (response != null) {
                emit(
                    Response.Success(
                        autocompletePlaceEntityMapper.mapFromEntity(response)
                    )
                )
                emit(Response.Loading(false))
            }
        }

    override suspend fun fetchPlace(city: String): Flow<Response<Place>> = flow {
        emit(Response.Loading(true))

        val response = try {
            placeRemoteDataSource.fetchPlace(city)
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error"))
            null
        }

        if (response != null) {
            placeCacheDataSource.addPlaceToCache(response)
            emit(Response.Success(placeEntityMapper.mapFromEntity(response)))
            emit(Response.Loading(false))
        }
    }

    override suspend fun fetchAllPlaces(): Flow<Response<List<Place>>> {
        val loading = flow<Response<List<Place>>> { emit(Response.Loading(true)) }
        val cachedPlaces: Flow<Response<List<Place>>> = placeCacheDataSource.getPlacesFromCache()
            .map { places -> places.map { placeEntityMapper.mapFromEntity(it) } }
            .map { Response.Success(it) }
        return merge(loading, cachedPlaces)
    }

    override suspend fun addPlace(place: Place) {
        placeCacheDataSource.addPlaceToCache(placeEntityMapper.mapToEntity(place))
    }

    override suspend fun deleteAllPlaces() {
        placeCacheDataSource.clearCache()
    }

    override suspend fun deletePlace(id: Int) {
        placeCacheDataSource.deletePlace(id)
    }
}