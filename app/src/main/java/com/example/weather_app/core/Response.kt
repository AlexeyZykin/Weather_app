package com.example.weather_app.core

sealed class Response<T> {
    data class Loading<T>(val isLoading: Boolean = false): Response<T>()
    data class Success<T>(val data: T): Response<T>()
    data class Error<T>(val msg: String): Response<T>()
}