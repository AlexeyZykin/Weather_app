package com.example.weather_app.data.source

import com.example.weather_app.data.model.CurrentWeatherEntity


interface WeatherRemoteDataSource {
    suspend fun fetchRealtimeWeather(lat: Double, lon: Double): CurrentWeatherEntity
}