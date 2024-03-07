package com.example.weather_app.presentation.koin

import com.example.weather_app.presentation.ui.details.ForecastDetailsViewModel
import com.example.weather_app.presentation.ui.home.WeatherViewModel
import com.example.weather_app.presentation.ui.place.LocationManagementViewModel
import com.example.weather_app.presentation.ui.place.SearchPlaceViewModel
import com.example.weather_app.presentation.mapper.weather.CityUiMapper
import com.example.weather_app.presentation.mapper.weather.CloudsUiMapper
import com.example.weather_app.presentation.mapper.weather.CoordinatesUiMapper
import com.example.weather_app.presentation.mapper.weather.CurrentWeatherUiMapper
import com.example.weather_app.presentation.mapper.weather.ForecastItemUiMapper
import com.example.weather_app.presentation.mapper.weather.ForecastWeatherUiMapper
import com.example.weather_app.presentation.mapper.weather.MainInfoUiMapper
import com.example.weather_app.presentation.mapper.weather.SysUiMapper
import com.example.weather_app.presentation.mapper.weather.WeatherUiMapper
import com.example.weather_app.presentation.mapper.weather.WindUiMapper
import com.example.weather_app.presentation.mapper.place.AutocompletePlaceUiMapper
import com.example.weather_app.presentation.mapper.place.PlaceUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { WeatherViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { ForecastDetailsViewModel(get(), get(), get()) }
    viewModel { SearchPlaceViewModel(get(), get(), get(), get(), get()) }
    viewModel { LocationManagementViewModel(get(), get(), get(), get()) }
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