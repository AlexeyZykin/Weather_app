package com.example.weather_app.remote.model

data class RealtimeWeatherResponse(
    val id: Int,
    val coord: CoordinatesResponse,
    val weather: List<WeatherResponse>,
    val main: MainInfoResponse,
    val wind: WindResponse,
    val name: String
)


