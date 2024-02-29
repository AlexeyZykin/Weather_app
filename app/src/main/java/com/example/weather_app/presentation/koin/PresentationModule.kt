package com.example.weather_app.presentation.koin

import com.example.weather_app.presentation.features.details.ForecastDetailsViewModel
import com.example.weather_app.presentation.features.home.WeatherViewModel
import com.example.weather_app.presentation.features.location.LocationViewModel
import com.example.weather_app.presentation.mapper.CityUiMapper
import com.example.weather_app.presentation.mapper.CloudsUiMapper
import com.example.weather_app.presentation.mapper.CoordinatesUiMapper
import com.example.weather_app.presentation.mapper.CurrentWeatherUiMapper
import com.example.weather_app.presentation.mapper.ForecastItemUiMapper
import com.example.weather_app.presentation.mapper.ForecastWeatherUiMapper
import com.example.weather_app.presentation.mapper.MainInfoUiMapper
import com.example.weather_app.presentation.mapper.SysUiMapper
import com.example.weather_app.presentation.mapper.WeatherUiMapper
import com.example.weather_app.presentation.mapper.WindUiMapper
import com.example.weather_app.presentation.mapper.place.AutocompletePlaceUiMapper
import com.example.weather_app.presentation.mapper.place.PlaceUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { WeatherViewModel(get(), get(), get(), get()) }
    viewModel { ForecastDetailsViewModel(get(), get(), get()) }
    viewModel { LocationViewModel(get(), get()) }
    factory { CloudsUiMapper() }
    factory { CoordinatesUiMapper() }
    factory { CurrentWeatherUiMapper(get(), get(), get(), get(), get(), get()) }
    factory { MainInfoUiMapper() }
    factory { SysUiMapper() }
    factory { WeatherUiMapper() }
    factory { WindUiMapper() }
    factory { CityUiMapper(get()) }
    factory { ForecastItemUiMapper(get(), get(), get(), get()) }
    factory { ForecastWeatherUiMapper(get(), get()) }
    factory { PlaceUiMapper() }
    factory { AutocompletePlaceUiMapper(get()) }
}