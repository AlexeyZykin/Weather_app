package com.example.weather_app.remote.model.weather


data class ForecastItemResponse(
    val dt: Long,
    val main: MainInfoResponse,
    val weather: List<WeatherResponse>,
    val visibility: Int,
    val pop: Double,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val sys: SysForecastResponse,
    val dt_txt: String
)
