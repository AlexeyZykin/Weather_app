package com.example.weather_app.domain.usecase

import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.weather.ForecastItem
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class FetchForecastByDayUseCase(private val weatherRepository: WeatherRepository) {
    fun invoke(dtTxt: String): Flow<Response<List<ForecastItem>>> {
        return weatherRepository.fetchForecastByDay(dtTxt)
    }
}