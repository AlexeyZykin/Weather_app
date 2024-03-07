package com.example.weather_app.data.koin

import com.example.weather_app.data.mapper.place.AutocompletePlaceEntityMapper
import com.example.weather_app.data.mapper.weather.CityEntityMapper
import com.example.weather_app.data.mapper.weather.CloudsEntityMapper
import com.example.weather_app.data.mapper.weather.CoordinatesEntityMapper
import com.example.weather_app.data.mapper.weather.CurrentWeatherEntityMapper
import com.example.weather_app.data.mapper.weather.ForecastItemEntityMapper
import com.example.weather_app.data.mapper.weather.ForecastWeatherEntityMapper
import com.example.weather_app.data.mapper.weather.MainInfoEntityMapper
import com.example.weather_app.data.mapper.place.PlaceEntityMapper
import com.example.weather_app.data.mapper.weather.SysEntityMapper
import com.example.weather_app.data.mapper.weather.WeatherEntityMapper
import com.example.weather_app.data.mapper.weather.WindEntityMapper
import com.example.weather_app.data.repository.PlaceRepositoryImpl
import com.example.weather_app.data.repository.WeatherRepositoryImpl
import com.example.weather_app.domain.repository.PlaceRepository
import com.example.weather_app.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get(), get(), get()) }
    single<PlaceRepository> { PlaceRepositoryImpl(get(), get(), get(), get()) }
    factory { CoordinatesEntityMapper() }
    factory { CurrentWeatherEntityMapper(get(), get(), get(), get(), get(), get()) }
    factory { MainInfoEntityMapper() }
    factory { SysEntityMapper() }
    factory { WeatherEntityMapper() }
    factory { WindEntityMapper() }
    factory { CloudsEntityMapper() }
    factory { CityEntityMapper(get()) }
    factory { ForecastItemEntityMapper(get(), get(), get(), get()) }
    factory { ForecastWeatherEntityMapper(get(), get()) }
    factory { PlaceEntityMapper() }
    factory { AutocompletePlaceEntityMapper(get()) }
}



