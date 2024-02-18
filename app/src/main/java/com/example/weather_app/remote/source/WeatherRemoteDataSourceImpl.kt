package com.example.weather_app.remote.source

import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.remote.mapper.CurrentWeatherResponseMapper

class WeatherRemoteDataSourceImpl(
    private val weatherService: WeatherService,
    private val currentWeatherResponseMapper: CurrentWeatherResponseMapper
    ) : WeatherRemoteDataSource {
    override suspend fun fetchRealtimeWeather(lat: Double, lon: Double): CurrentWeatherEntity {
        return currentWeatherResponseMapper.mapFromResponse(weatherService.getRealtimeWeather(lat = lat, lon = lon))
    }
}