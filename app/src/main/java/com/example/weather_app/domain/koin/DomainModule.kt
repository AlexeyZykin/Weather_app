package com.example.weather_app.domain.koin

import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchRealtimeWeatherUseCase(get()) }
}