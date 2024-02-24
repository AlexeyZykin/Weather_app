package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.model.ForecastItem
import com.example.weather_app.domain.model.ForecastWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>>
    suspend fun fetchForecast(lat: Double, lon: Double): Flow<Response<ForecastWeather>>
    suspend fun fetchForecastByTime(dt: Long): Flow<Response<ForecastItem>>
    suspend fun fetchForecastByDay(dtTxt: String): Flow<Response<List<ForecastItem>>>
}