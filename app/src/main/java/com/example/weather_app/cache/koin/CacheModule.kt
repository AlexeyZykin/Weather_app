package com.example.weather_app.cache.koin

import android.content.Context
import androidx.room.Room
import com.example.weather_app.cache.mapper.CityCacheMapper
import com.example.weather_app.cache.mapper.CloudsCacheMapper
import com.example.weather_app.cache.mapper.CoordinatesCacheMapper
import com.example.weather_app.cache.mapper.CurrentWeatherCacheMapper
import com.example.weather_app.cache.mapper.ForecastItemCacheMapper
import com.example.weather_app.cache.mapper.ForecastWeatherCacheMapper
import com.example.weather_app.cache.mapper.MainInfoCacheMapper
import com.example.weather_app.cache.mapper.SysCacheMapper
import com.example.weather_app.cache.mapper.WeatherCacheMapper
import com.example.weather_app.cache.mapper.WindCacheMapper
import com.example.weather_app.cache.room.db.WeatherDatabase
import com.example.weather_app.cache.source.WeatherCacheDataSourceImpl
import com.example.weather_app.core.Constants
import com.example.weather_app.data.source.WeatherCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module {
    single { provideDatabase(androidContext()) }
    single { provideCurrentWeatherDao(get()) }
    single { provideForecastWeatherDao(get()) }
    single<WeatherCacheDataSource> { WeatherCacheDataSourceImpl(get(), get(), get(), get()) }
    factory { CloudsCacheMapper() }
    factory { CoordinatesCacheMapper() }
    factory { CurrentWeatherCacheMapper(get(), get(), get(), get(), get(), get()) }
    factory { MainInfoCacheMapper() }
    factory { SysCacheMapper() }
    factory { WeatherCacheMapper() }
    factory { WindCacheMapper() }
    factory { CityCacheMapper(get()) }
    factory { ForecastItemCacheMapper(get(), get(), get(), get()) }
    factory { ForecastWeatherCacheMapper(get(), get()) }
}

private fun provideDatabase(context: Context): WeatherDatabase {
    return Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        Constants.ROOM_DATABASE_NAME
    ).build()
}

private fun provideCurrentWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.getCurrentWeatherDao()

private fun provideForecastWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.getForecastWeatherDao()