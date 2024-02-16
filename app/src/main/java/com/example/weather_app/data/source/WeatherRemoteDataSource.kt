package com.example.weather_app.data.source

import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.remote.model.CurrentWeatherResponse


interface WeatherRemoteDataSource {
    suspend fun fetchRealtimeWeather(): CurrentWeatherEntity
}