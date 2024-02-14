package com.example.weather_app.remote.model

data class CurrentWeatherResponse(
    val id: Int,
    val coord: CoordinatesResponse,
    val weather: List<WeatherResponse>,
    val main: MainInfoResponse,
    val wind: WindResponse,
    val dt: Long,
    val name: String,
    val sys: SysResponse
)


