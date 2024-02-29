package com.example.weather_app.remote.model.place

data class PlaceResponse(
    val city: String,
    val lon: Double,
    val lat: Double,
    val place_id: String
)