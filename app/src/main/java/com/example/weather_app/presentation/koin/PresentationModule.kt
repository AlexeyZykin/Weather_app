package com.example.weather_app.presentation.koin

import com.example.weather_app.presentation.home.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { WeatherViewModel(get()) }
}