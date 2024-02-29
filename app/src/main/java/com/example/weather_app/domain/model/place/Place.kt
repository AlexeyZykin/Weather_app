package com.example.weather_app.domain.model.place

data class Place(
    val city: String,
    val lon: Double,
    val lat: Double,
    val placeIdStr: String
)
