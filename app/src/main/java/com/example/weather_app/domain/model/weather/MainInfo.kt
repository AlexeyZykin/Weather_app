package com.example.weather_app.domain.model.weather

data class MainInfo(
    val temp: Int,
    val feelsLike: Int,
    val pressure: Int,
    val humidity: Int
)