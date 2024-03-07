package com.example.weather_app.presentation.model.weather


data class ForecastWeatherUi(
    val cnt: Int,
    val forecastList: List<ForecastItemUi>,
    val city: CityUi
)
