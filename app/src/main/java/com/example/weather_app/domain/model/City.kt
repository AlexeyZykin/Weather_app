package com.example.weather_app.domain.model


data class City(
    val id: Int,
    val name: String,
    val coord: Coordinates,
    val country: String,
    val population: Int,
    val sunrise: Long,
    val sunset: Long
)