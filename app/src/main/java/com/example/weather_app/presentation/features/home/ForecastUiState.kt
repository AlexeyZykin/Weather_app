package com.example.weather_app.presentation.features.home

import com.example.weather_app.presentation.model.ForecastWeatherUi


sealed class ForecastUiState {
    data object Loading: ForecastUiState()
    data class Success(val data: ForecastWeatherUi): ForecastUiState()
    data class Error(val msg: String): ForecastUiState()
}