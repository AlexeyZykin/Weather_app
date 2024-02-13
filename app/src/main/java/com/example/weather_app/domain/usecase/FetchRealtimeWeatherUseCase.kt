package com.example.weather_app.domain.usecase

import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.model.RealtimeWeatherResponse
import kotlinx.coroutines.flow.Flow

class FetchRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(): Flow<RealtimeWeatherResponse> {
        return weatherRepository.fetchRealtimeWeather()
    }
}