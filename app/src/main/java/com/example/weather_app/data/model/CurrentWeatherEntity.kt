package com.example.weather_app.data.model


data class CurrentWeatherEntity(
    val id: Int,
    val coord: CoordinatesEntity,
    val weather: List<WeatherEntity>,
    val main: MainInfoEntity,
    val wind: WindEntity,
    val dt: Long,
    val name: String,
    val sys: SysEntity
)
