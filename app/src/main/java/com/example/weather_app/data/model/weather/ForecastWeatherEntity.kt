package com.example.weather_app.data.model.weather

import com.example.weather_app.data.model.weather.CityEntity
import com.example.weather_app.data.model.weather.ForecastItemEntity

data class ForecastWeatherEntity(
    val cnt: Int,
    val forecastList: List<ForecastItemEntity>,
    val city: CityEntity
)
