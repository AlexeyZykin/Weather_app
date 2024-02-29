package com.example.weather_app.remote.model.weather

data class MainInfoResponse(
    val temp: Float,
    val feels_like: Float,
    val pressure: Int,
    val humidity: Int
)