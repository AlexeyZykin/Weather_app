package com.example.weather_app.data.koin

import com.example.weather_app.data.repository.WeatherRepositoryImpl
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.remote.constants.ApiConstants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}



