package com.example.weather_app.remote.model

data class CurrentWeatherResponse(
    val id: Int,
    val coord: CoordinatesResponse,
    val weather: List<WeatherResponse>,
    val main: MainInfoResponse,
    val visibility: Int,
    val wind: WindResponse,
    val clouds: CloudsResponse,
    val dt: Long,
    val name: String,
    val sys: SysResponse
)


