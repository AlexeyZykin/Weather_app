package com.example.weather_app.domain.model.weather

import com.example.weather_app.domain.model.weather.City
import com.example.weather_app.domain.model.weather.ForecastItem


data class ForecastWeather(
    val cnt: Int,
    val forecastList: List<ForecastItem>,
    val city: City
)