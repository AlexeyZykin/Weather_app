package com.example.weather_app.data.model

data class ForecastItemEntity(
    val dt: Long,
    val mainInfo: MainInfoEntity,
    val weather: WeatherEntity,
    val visibility: Int,
    val pop: Double,
    val clouds: CloudsEntity,
    val wind: WindEntity,
    val partOfDay: String,
    val dtTxt: String
)
