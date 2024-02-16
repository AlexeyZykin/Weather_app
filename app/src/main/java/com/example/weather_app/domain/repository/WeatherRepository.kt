package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.remote.model.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchRealtimeWeather(): Flow<Response<CurrentWeather>>
}