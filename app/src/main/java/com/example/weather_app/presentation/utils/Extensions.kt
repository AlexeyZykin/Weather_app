package com.example.weather_app.presentation.utils

import com.example.weather_app.R
import com.example.weather_app.presentation.model.weather.WeatherType

fun WeatherType.imageRes(isNight: Boolean): Int = if (isNight) {
    when (this) {
        WeatherType.THUNDERSTORM_RAIN -> R.drawable.image_thunder_rain
        WeatherType.THUNDERSTORM -> R.drawable.image_night_thunderstorm
        WeatherType.DRIZZLE -> R.drawable.image_drizzle
        WeatherType.HEAVY_DRIZZLE -> R.drawable.image_night_heavy_drizzle
        WeatherType.LIGHT_RAIN -> R.drawable.image_night_light_rain
        WeatherType.RAIN -> R.drawable.image_night_rain
        WeatherType.HEAVY_RAIN -> R.drawable.image_night_heavy_rain
        WeatherType.SNOW -> R.drawable.image_night_light_snow
        WeatherType.HEAVY_SNOW -> R.drawable.image_night_heavy_snow
        WeatherType.SNOW_WITH_RAIN -> R.drawable.image_snow_with_rain
        WeatherType.MIST -> R.drawable.image_night_mist
        WeatherType.HAZE -> R.drawable.image_night_haze
        WeatherType.FOG -> R.drawable.image_night_mist
        WeatherType.TORNADO -> R.drawable.image_tornado
        WeatherType.CLEAR -> R.drawable.image_night_clear
        WeatherType.FEW_CLOUDS -> R.drawable.image_night_few_clouds
        WeatherType.CLOUDS -> R.drawable.image_night_clouds
        WeatherType.BROKEN_CLOUDS -> R.drawable.image_night_broken_clouds
        WeatherType.OVERCAST -> R.drawable.image_night_overcast
        WeatherType.UNDEFINED -> R.drawable.image_default
    }
} else
    when (this) {
        WeatherType.THUNDERSTORM_RAIN -> R.drawable.image_thunder_rain
        WeatherType.THUNDERSTORM -> R.drawable.image_day_thunderstorm
        WeatherType.DRIZZLE -> R.drawable.image_day_drizzle
        WeatherType.HEAVY_DRIZZLE -> R.drawable.image_day_heavy_drizzle
        WeatherType.LIGHT_RAIN -> R.drawable.image_day_light_rain
        WeatherType.RAIN -> R.drawable.image_day_rain
        WeatherType.HEAVY_RAIN -> R.drawable.image_day_heavy_rain
        WeatherType.SNOW -> R.drawable.image_day_snow
        WeatherType.HEAVY_SNOW -> R.drawable.image_day_heavy_snow
        WeatherType.SNOW_WITH_RAIN -> R.drawable.image_snow_with_rain
        WeatherType.MIST -> R.drawable.image_day_mist
        WeatherType.HAZE -> R.drawable.image_day_haze
        WeatherType.FOG -> R.drawable.image_day_fog
        WeatherType.TORNADO -> R.drawable.image_tornado
        WeatherType.CLEAR -> R.drawable.image_day_clear
        WeatherType.FEW_CLOUDS -> R.drawable.image_day_few_clouds
        WeatherType.CLOUDS -> R.drawable.image_day_clouds
        WeatherType.BROKEN_CLOUDS -> R.drawable.image_day_broken_clouds
        WeatherType.OVERCAST -> R.drawable.image_day_overcast
        WeatherType.UNDEFINED -> R.drawable.image_default
    }

fun WeatherType.iconRes(partOfDay: String): Int {
    return if (partOfDay == "n") {
        when (this) {
            WeatherType.THUNDERSTORM_RAIN -> R.drawable.ic_thunderstorm_rain
            WeatherType.THUNDERSTORM -> R.drawable.ic_thunder
            WeatherType.DRIZZLE -> R.drawable.ic_light_drizzle
            WeatherType.HEAVY_DRIZZLE -> R.drawable.ic_drizzle
            WeatherType.LIGHT_RAIN -> R.drawable.ic_light_rain
            WeatherType.RAIN -> R.drawable.ic_rain
            WeatherType.HEAVY_RAIN -> R.drawable.ic_drizzle
            WeatherType.SNOW -> R.drawable.ic_snow
            WeatherType.HEAVY_SNOW -> R.drawable.ic_heavy_snow
            WeatherType.SNOW_WITH_RAIN -> R.drawable.ic_snow_with_rain
            WeatherType.MIST -> R.drawable.ic_fog
            WeatherType.HAZE -> R.drawable.ic_fog
            WeatherType.FOG -> R.drawable.ic_fog
            WeatherType.TORNADO -> R.drawable.ic_tornado
            WeatherType.CLEAR -> R.drawable.ic_night_clear
            WeatherType.FEW_CLOUDS -> R.drawable.ic_night_few_clouds
            WeatherType.CLOUDS -> R.drawable.ic_clouds
            WeatherType.BROKEN_CLOUDS -> R.drawable.ic_broken_clouds
            WeatherType.OVERCAST -> R.drawable.ic_broken_clouds
            WeatherType.UNDEFINED -> R.drawable.ic_fog
        }
    }
    else {
        when (this) {
            WeatherType.THUNDERSTORM_RAIN -> R.drawable.ic_thunderstorm_rain
            WeatherType.THUNDERSTORM -> R.drawable.ic_thunder
            WeatherType.DRIZZLE -> R.drawable.ic_light_drizzle
            WeatherType.HEAVY_DRIZZLE -> R.drawable.ic_drizzle
            WeatherType.LIGHT_RAIN -> R.drawable.ic_light_rain
            WeatherType.RAIN -> R.drawable.ic_rain
            WeatherType.HEAVY_RAIN -> R.drawable.ic_drizzle
            WeatherType.SNOW -> R.drawable.ic_snow
            WeatherType.HEAVY_SNOW -> R.drawable.ic_heavy_snow
            WeatherType.SNOW_WITH_RAIN -> R.drawable.ic_snow_with_rain
            WeatherType.MIST -> R.drawable.ic_fog
            WeatherType.HAZE -> R.drawable.ic_fog
            WeatherType.FOG -> R.drawable.ic_fog
            WeatherType.TORNADO -> R.drawable.ic_tornado
            WeatherType.CLEAR -> R.drawable.ic_day_clear
            WeatherType.FEW_CLOUDS -> R.drawable.ic_day_few_clouds
            WeatherType.CLOUDS -> R.drawable.ic_clouds
            WeatherType.BROKEN_CLOUDS -> R.drawable.ic_broken_clouds
            WeatherType.OVERCAST -> R.drawable.ic_broken_clouds
            WeatherType.UNDEFINED -> R.drawable.ic_fog
        }
    }
}