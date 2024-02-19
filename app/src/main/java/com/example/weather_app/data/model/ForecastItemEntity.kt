package com.example.weather_app.data.model

import com.example.weather_app.remote.model.CloudsResponse
import com.example.weather_app.remote.model.MainInfoResponse
import com.example.weather_app.remote.model.SysForecastResponse
import com.example.weather_app.remote.model.WeatherResponse
import com.example.weather_app.remote.model.WindResponse

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
