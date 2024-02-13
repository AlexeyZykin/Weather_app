package com.example.weather_app.remote.source

import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.remote.model.RealtimeWeatherResponse

class WeatherRemoteDataSourceImpl(private val weatherService: WeatherService) : WeatherRemoteDataSource {
    override suspend fun fetchRealtimeWeather(): RealtimeWeatherResponse {
        return weatherService.getRealtimeWeather()
    }
}