package com.example.weather_app.remote.model

data class ForecastWeatherResponse(
    val cnt: Int,
    val list: List<ForecastItemResponse>,
    val city: CityResponse
)