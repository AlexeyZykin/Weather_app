package com.example.weather_app.data.model

import com.example.weather_app.remote.model.CityResponse

data class ForecastWeatherEntity(
    val cnt: Int,
    val forecastList: List<ForecastItemEntity>,
    val city: CityEntity
)
