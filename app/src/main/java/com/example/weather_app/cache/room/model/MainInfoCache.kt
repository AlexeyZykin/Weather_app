package com.example.weather_app.cache.room.model

data class MainInfoCache(
    val temp: Int,
    val feelsLike: Int,
    val pressure: Int,
    val humidity: Int
)