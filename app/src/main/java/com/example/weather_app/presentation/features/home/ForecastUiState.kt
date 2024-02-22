package com.example.weather_app.presentation.features.home

import com.example.weather_app.presentation.model.ForecastItemUi



sealed class ForecastUiState {
    data object Loading: ForecastUiState()
    data class Success(val data: List<ForecastItemUi>): ForecastUiState()
    data class Error(val msg: String): ForecastUiState()
}