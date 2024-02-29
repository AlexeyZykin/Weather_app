package com.example.weather_app.data.model.weather

data class CityEntity(
    val id: Int,
    val name: String,
    val coord: CoordinatesEntity,
    val country: String,
    val population: Int,
    val sunrise: Long,
    val sunset: Long
)