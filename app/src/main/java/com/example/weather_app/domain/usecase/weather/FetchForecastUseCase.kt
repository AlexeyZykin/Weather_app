package com.example.weather_app.domain.usecase.weather

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.weather.ForecastWeather
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class FetchForecastUseCase(private val weatherRepository: WeatherRepository) {
    fun invoke(lat: Double, lon: Double): Flow<Response<ForecastWeather>> {
        return weatherRepository.fetchForecast(lat, lon)
    }
}