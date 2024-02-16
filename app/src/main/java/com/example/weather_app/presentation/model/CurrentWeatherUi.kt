package com.example.weather_app.presentation.model

import com.example.weather_app.domain.model.Clouds
import com.example.weather_app.domain.model.Coordinates
import com.example.weather_app.domain.model.MainInfo
import com.example.weather_app.domain.model.Sys
import com.example.weather_app.domain.model.Weather
import com.example.weather_app.domain.model.Wind

data class CurrentWeatherUi(
    val id: Int,
    val coord: CoordinatesUi,
    val weather: List<WeatherUi>,
    val main: MainInfoUi,
    val visibility: Int,
    val wind: WindUi,
    val clouds: CloudsUi,
    val dt: Long,
    val name: String,
    val sys: SysUi
)
