package com.example.weather_app.presentation.utils

sealed class UiState<T> {
    data class Loading<T>(val isLoading: Boolean): UiState<T>()
    data class Success<T>(val data: T? = null): UiState<T>()
    data class Error<T>(val msg: String? = null): UiState<T>()
}