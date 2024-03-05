package com.example.weather_app.domain.repository

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.weather.CurrentWeather
import com.example.weather_app.domain.model.weather.ForecastItem
import com.example.weather_app.domain.model.weather.ForecastWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>>
    fun fetchForecast(lat: Double, lon: Double): Flow<Response<ForecastWeather>>
    fun fetchForecastByTime(dt: Long): Flow<Response<ForecastItem>>
    fun fetchForecastByDay(dtTxt: String): Flow<Response<List<ForecastItem>>>
}