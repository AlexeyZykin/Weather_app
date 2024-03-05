package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.weather.CurrentWeather
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class FetchRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {
    fun invoke(lat: Double, lon: Double): Flow<Response<CurrentWeather>> {
        return weatherRepository.fetchRealtimeWeather(lat, lon)
    }
}