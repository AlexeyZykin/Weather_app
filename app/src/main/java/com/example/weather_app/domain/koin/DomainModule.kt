package com.example.weather_app.domain.koin

import com.example.weather_app.domain.usecase.place.AddPlaceUseCase
import com.example.weather_app.domain.usecase.place.DeleteAllPlacesUseCase
import com.example.weather_app.domain.usecase.place.DeletePlaceUseCase
import com.example.weather_app.domain.usecase.place.FetchAllPlacesUseCase
import com.example.weather_app.domain.usecase.place.FetchAutocompletePlacesUseCase
import com.example.weather_app.domain.usecase.weather.FetchForecastByDayUseCase
import com.example.weather_app.domain.usecase.weather.FetchForecastByTimeUseCase
import com.example.weather_app.domain.usecase.weather.FetchForecastUseCase
import com.example.weather_app.domain.usecase.place.FetchPlaceUseCase
import com.example.weather_app.domain.usecase.weather.FetchRealtimeWeatherUseCase
import com.example.weather_app.domain.usecase.place.LoadPlaceUseCase
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
    factory { LoadPlaceUseCase(get()) }
}