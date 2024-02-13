package com.example.weather_app.remote.model

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
