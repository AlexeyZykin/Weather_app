package com.example.weather_app.remote.source

import com.example.weather_app.data.model.weather.CurrentWeatherEntity
import com.example.weather_app.data.model.weather.ForecastWeatherEntity
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.remote.mapper.weather.CurrentWeatherResponseMapper
import com.example.weather_app.remote.mapper.weather.ForecastWeatherResponseMapper

class WeatherRemoteDataSourceImpl(
    private val weatherService: WeatherService,
    private val currentWeatherResponseMapper: CurrentWeatherResponseMapper,
    private val forecastWeatherResponseMapper: ForecastWeatherResponseMapper
    ) : WeatherRemoteDataSource {
    override suspend fun fetchRealtimeWeather(lat: Double, lon: Double): CurrentWeatherEntity {
        return currentWeatherResponseMapper.mapFromResponse(weatherService.getRealtimeWeather(lat = lat, lon = lon))
    }

    override suspend fun fetchForecast(lat: Double, lon: Double): ForecastWeatherEntity {
        return forecastWeatherResponseMapper.mapFromResponse(weatherService.getForecast(lat, lon))
    }
}