package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.ForecastItem
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class FetchForecastByTimeUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(dt: Long): Flow<Response<ForecastItem>> {
        return weatherRepository.fetchForecastByTime(dt)
    }
}