package com.example.weather_app.data.model

data class ForecastWeatherEntity(
    val cnt: Int,
    val forecastList: List<ForecastItemEntity>,
    val city: CityEntity
)
