package com.example.weather_app.presentation.mapper

import com.example.weather_app.presentation.model.WeatherType

fun mapIdToWeatherType(typeId: Int) = when (typeId) {
    in 200..202, in 230..232 -> WeatherType.THUNDERSTORM_RAIN
    in 210..221 -> WeatherType.THUNDERSTORM
    in 300..311 -> WeatherType.DRIZZLE
    in 312..321 -> WeatherType.HEAVY_DRIZZLE
    in 500..501 -> WeatherType.LIGHT_RAIN
    in 502..511 -> WeatherType.RAIN
    in 520..531 -> WeatherType.HEAVY_RAIN
    in 600..601 -> WeatherType.SNOW
    in 602..611 -> WeatherType.HEAVY_SNOW
    in 612..622 -> WeatherType.SNOW_WITH_RAIN
    701, 711 -> WeatherType.MIST
    721 -> WeatherType.HAZE
    741 -> WeatherType.FOG
    781 -> WeatherType.TORNADO
    800 -> WeatherType.CLEAR
    801 -> WeatherType.FEW_CLOUDS
    802 -> WeatherType.CLOUDS
    803 -> WeatherType.BROKEN_CLOUDS
    804 -> WeatherType.OVERCAST
    else -> WeatherType.UNDEFINED
}