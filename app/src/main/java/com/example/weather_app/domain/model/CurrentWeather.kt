package com.example.weather_app.domain.model

data class CurrentWeather(
    val id: Int,
    val coord: Coordinates,
    val weather: Weather,
    val main: MainInfo,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val name: String,
    val sys: Sys
)
