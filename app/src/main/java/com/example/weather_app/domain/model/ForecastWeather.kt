package com.example.weather_app.domain.model


data class ForecastWeather(
    val cnt: Int,
    val forecastList: List<ForecastItem>,
    val city: City
)