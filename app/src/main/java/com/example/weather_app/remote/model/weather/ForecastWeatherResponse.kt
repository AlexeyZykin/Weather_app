package com.example.weather_app.remote.model.weather

import com.example.weather_app.remote.model.weather.CityResponse
import com.example.weather_app.remote.model.weather.ForecastItemResponse

data class ForecastWeatherResponse(
    val cnt: Int,
    val list: List<ForecastItemResponse>,
    val city: CityResponse
)