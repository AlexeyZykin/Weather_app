package com.example.weather_app.data.model.place

data class PlaceEntity(
    val id: Int?,
    val city: String,
    val lon: Double,
    val lat: Double,
    val placeIdStr: String
)
