package com.example.weather_app.domain.model.weather

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)