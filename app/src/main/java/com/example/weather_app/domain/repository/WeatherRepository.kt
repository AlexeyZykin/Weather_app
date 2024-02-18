package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>>
}