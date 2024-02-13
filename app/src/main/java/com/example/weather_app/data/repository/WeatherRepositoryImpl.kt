package com.example.weather_app.data.repository

import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.model.RealtimeWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun fetchRealtimeWeather(): Flow<RealtimeWeatherResponse> = flow {
        emit(weatherRemoteDataSource.fetchRealtimeWeather())
    }
}