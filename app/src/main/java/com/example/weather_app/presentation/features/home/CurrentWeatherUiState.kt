package com.example.weather_app.presentation.features.home

import com.example.weather_app.presentation.model.CurrentWeatherUi

sealed class CurrentWeatherUiState {
    data object Loading: CurrentWeatherUiState()
    data class Success(val data: CurrentWeatherUi): CurrentWeatherUiState()
    data class Error(val msg: String): CurrentWeatherUiState()
}