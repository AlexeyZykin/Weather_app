package com.example.weather_app.data.source

import com.example.weather_app.remote.model.RealtimeWeatherResponse

interface WeatherRemoteDataSource {
    suspend fun fetchRealtimeWeather(): RealtimeWeatherResponse
}