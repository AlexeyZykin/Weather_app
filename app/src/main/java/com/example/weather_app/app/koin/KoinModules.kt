package com.example.weather_app.app.koin

import com.example.weather_app.cache.koin.cacheModule
import com.example.weather_app.data.koin.dataModule
import com.example.weather_app.domain.koin.domainModule
import com.example.weather_app.presentation.koin.presentationModule
import com.example.weather_app.remote.koin.placesApiModule
import com.example.weather_app.remote.koin.weatherApiModule

val koinModules = listOf(
    dataModule,
    weatherApiModule,
    placesApiModule,
    cacheModule,
    domainModule,
    presentationModule
)