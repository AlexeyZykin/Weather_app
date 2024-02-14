package com.example.weather_app.data.repository

import com.example.weather_app.data.mapper.CurrentWeatherEntityMapper
import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.model.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper
) : WeatherRepository {
    override suspend fun fetchRealtimeWeather(): Flow<CurrentWeather> = flow {
        emit(
            currentWeatherEntityMapper.mapFromEntity(weatherRemoteDataSource.fetchRealtimeWeather())
        )
    }
}