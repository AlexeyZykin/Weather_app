package com.example.weather_app.data.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.mapper.AutocompleteEntityMapper
import com.example.weather_app.data.model.place.AutocompletePlaceEntity
import com.example.weather_app.data.source.PlaceSearchRemoteDataSource
import com.example.weather_app.domain.model.place.AutocompletePlace
import com.example.weather_app.domain.repository.PlaceSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaceSearchRepositoryImpl(
    private val placeSearchRemoteDataSource: PlaceSearchRemoteDataSource,
    private val autocompleteEntityMapper: AutocompleteEntityMapper
) :
    PlaceSearchRepository {
    override suspend fun fetchAutocompletePlaces(textInput: String): Flow<Response<AutocompletePlace>> =
        flow {
            emit(Response.Loading(true))

            val response = try {
                placeSearchRemoteDataSource.fetchAutocompletePlaces(textInput)
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "Error"))
                null
            }

            if (response != null) {
                emit(
                    Response.Success(
                        autocompleteEntityMapper.mapFromEntity(response)
                    )
                )
                emit(Response.Loading(false))
            }
        }
}