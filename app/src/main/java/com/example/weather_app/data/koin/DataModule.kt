package com.example.weather_app.data.koin

import com.example.weather_app.data.mapper.CoordinatesEntityMapper
import com.example.weather_app.data.mapper.CurrentWeatherEntityMapper
import com.example.weather_app.data.mapper.MainInfoEntityMapper
import com.example.weather_app.data.mapper.SysEntityMapper
import com.example.weather_app.data.mapper.WeatherEntityMapper
import com.example.weather_app.data.mapper.WindEntityMapper
import com.example.weather_app.data.repository.WeatherRepositoryImpl
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.constants.ApiConstants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }

    factory { CoordinatesEntityMapper() }
    factory { CurrentWeatherEntityMapper(get(), get(), get(), get(), get()) }
    factory { MainInfoEntityMapper() }
    factory { SysEntityMapper() }
    factory { WeatherEntityMapper() }
    factory { WindEntityMapper() }
}



