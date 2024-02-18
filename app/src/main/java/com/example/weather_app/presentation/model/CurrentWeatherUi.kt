package com.example.weather_app.presentation.model

data class CurrentWeatherUi(
    val id: Int,
    val coord: CoordinatesUi,
    val weather: WeatherUi,
    val weatherType: WeatherType,
    val main: MainInfoUi,
    val visibility: Int,
    val wind: WindUi,
    val clouds: CloudsUi,
    val dt: Long,
    val name: String,
    val sys: SysUi
)
