package com.example.weather_app.data.source

import com.example.weather_app.data.model.weather.CurrentWeatherEntity
import com.example.weather_app.data.model.weather.ForecastWeatherEntity


interface WeatherRemoteDataSource {
    suspend fun fetchRealtimeWeather(lat: Double, lon: Double): CurrentWeatherEntity
    suspend fun fetchForecast(lat: Double, lon: Double): ForecastWeatherEntity
}



