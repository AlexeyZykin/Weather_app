package com.example.weather_app.domain.model

data class CurrentWeather(
    val id: Int,
    val coord: Coordinates,
    val weather: List<Weather>,
    val main: MainInfo,
    val wind: Wind,
    val dt: Long,
    val name: String,
    val sys: Sys
)
