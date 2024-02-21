package com.example.weather_app.presentation.model


data class ForecastWeatherUi(
    val cnt: Int,
    val forecastList: List<ForecastItemUi>,
    val city: CityUi
)
