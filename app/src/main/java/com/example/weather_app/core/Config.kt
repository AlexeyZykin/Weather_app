package com.example.weather_app.core

object Config {
    const val BASE_URL_PLACE_API = "https://api.geoapify.com/v1/geocode/"
    const val BASE_URL_WEATHER_API = "https://api.openweathermap.org/data/2.5/"
    const val UNIT_MEASURE = "metric"
    const val ROOM_DATABASE_NAME = "weather.db"
    const val ROOM_CURRENT_WEATHER_TABLE_NAME = "current_weather"
    const val ROOM_FORECAST_WEATHER_TABLE_NAME = "forecast_weather"
    const val ROOM_PLACE_TABLE_NAME = "place"
    const val APP_PREFS = "weather app prefs"
    const val SHARED_PREFS_SELECTED_PLACE = "selected place"
    const val SHARED_PREFS_CURRENT_PLACE = "current place"
}