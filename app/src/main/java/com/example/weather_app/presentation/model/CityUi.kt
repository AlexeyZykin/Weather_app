package com.example.weather_app.presentation.model


data class CityUi(
    val id: Int,
    val name: String,
    val coord: CoordinatesUi,
    val country: String,
    val population: Int,
    val sunrise: Long,
    val sunset: Long
)