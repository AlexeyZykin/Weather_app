package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.model.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow

class FetchRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(): Flow<Response<CurrentWeather>> {
        return weatherRepository.fetchRealtimeWeather()
    }
}