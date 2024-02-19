package com.example.weather_app.remote.model


data class ForecastItemResponse(
    val dt: Long,
    val mainInfo: MainInfoResponse,
    val weather: WeatherResponse,
    val visibility: Int,
    val pop: Double,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val sys: SysForecastResponse,
    val dt_txt: String
)
