package com.example.weather_app.domain.repository

import com.example.weather_app.remote.model.RealtimeWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchRealtimeWeather(): Flow<RealtimeWeatherResponse>
}