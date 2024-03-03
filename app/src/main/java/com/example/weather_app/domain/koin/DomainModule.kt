package com.example.weather_app.domain.koin

import com.example.weather_app.domain.usecase.AddPlaceUseCase
import com.example.weather_app.domain.usecase.DeleteAllPlacesUseCase
import com.example.weather_app.domain.usecase.DeletePlaceUseCase
import com.example.weather_app.domain.usecase.FetchAllPlacesUseCase
import com.example.weather_app.domain.usecase.FetchAutocompletePlacesUseCase
import com.example.weather_app.domain.usecase.FetchForecastByDayUseCase
import com.example.weather_app.domain.usecase.FetchForecastByTimeUseCase
import com.example.weather_app.domain.usecase.FetchForecastUseCase
import com.example.weather_app.domain.usecase.FetchPlaceUseCase
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.domain.usecase.LoadPlaceUseCase
import com.example.weather_app.domain.usecase.UpdatePlaceUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchRealtimeWeatherUseCase(get()) }
    factory { FetchForecastUseCase(get()) }
    factory { FetchForecastByTimeUseCase(get()) }
    factory { FetchForecastByDayUseCase(get()) }

    factory { FetchAutocompletePlacesUseCase(get()) }
    factory { FetchPlaceUseCase(get()) }
    factory { FetchAllPlacesUseCase(get()) }
    factory { AddPlaceUseCase(get()) }
    factory { DeleteAllPlacesUseCase(get()) }
    factory { DeletePlaceUseCase(get()) }
    factory { UpdatePlaceUseCase(get()) }
    factory { LoadPlaceUseCase(get()) }
}