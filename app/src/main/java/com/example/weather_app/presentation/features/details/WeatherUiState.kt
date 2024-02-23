package com.example.weather_app.presentation.features.details

sealed class WeatherUiState<T> {
    data class Loading<T>(val isLoading: Boolean): WeatherUiState<T>()
    data class Success<T>(val data: T? = null): WeatherUiState<T>()
    data class Error<T>(val msg: String? = null): WeatherUiState<T>()
}