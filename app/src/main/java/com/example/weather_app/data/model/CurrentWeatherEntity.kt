package com.example.weather_app.data.model


data class CurrentWeatherEntity(
    val id: Int,
    val coord: CoordinatesEntity,
    val weather: WeatherEntity,
    val main: MainInfoEntity,
    val visibility: Int,
    val wind: WindEntity,
    val clouds: CloudsEntity,
    val dt: Long,
    val name: String,
    val sys: SysEntity
)
