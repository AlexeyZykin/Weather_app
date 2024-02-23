package com.example.weather_app.domain.koin

import com.example.weather_app.domain.usecase.FetchForecastByTimeUseCase
import com.example.weather_app.domain.usecase.FetchForecastUseCase
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchRealtimeWeatherUseCase(get()) }
    factory { FetchForecastUseCase(get()) }
    factory { FetchForecastByTimeUseCase(get()) }
}