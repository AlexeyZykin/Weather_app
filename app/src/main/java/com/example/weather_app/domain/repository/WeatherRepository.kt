package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.model.ForecastWeatherEntity
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.remote.model.ForecastWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>>
    suspend fun fetchForecast(lat: Double, lon: Double): Flow<Response<ForecastWeatherEntity>>
}