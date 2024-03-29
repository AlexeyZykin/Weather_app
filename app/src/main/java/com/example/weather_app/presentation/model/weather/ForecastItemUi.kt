package com.example.weather_app.presentation.model.weather


data class ForecastItemUi(
    val dt: Long,
    val mainInfo: MainInfoUi,
    val weather: WeatherUi,
    val weatherType: WeatherType,
    val visibility: Int,
    val probabilityOfPrecipitation: Int,
    val clouds: CloudsUi,
    val wind: WindUi,
    val partOfDay: String,
    val dtTxt: String
)