package com.example.weather_app.presentation.model.place

data class PlaceUi(
    val id: Int?,
    val city: String,
    val lon: Double,
    val lat: Double,
    val placeIdStr: String,
    val isCurrentPlace: Boolean = false,
    val isTarget: Boolean = false
)