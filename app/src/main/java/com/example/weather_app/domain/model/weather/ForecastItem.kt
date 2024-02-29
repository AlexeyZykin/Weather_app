package com.example.weather_app.domain.model.weather


data class ForecastItem(
    val dt: Long,
    val mainInfo: MainInfo,
    val weather: Weather,
    val visibility: Int,
    val pop: Double,
    val clouds: Clouds,
    val wind: Wind,
    val partOfDay: String,
    val dtTxt: String
)
