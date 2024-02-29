package com.example.weather_app.remote.model.weather

data class CityResponse(
    val id: Int,
    val name: String,
    val coord: CoordinatesResponse,
    val country: String,
    val population: Int,
    val sunrise: Long,
    val sunset: Long
)
