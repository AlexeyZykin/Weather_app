package com.example.weather_app.cache.koin

import android.content.Context
import androidx.room.Room
import com.example.weather_app.cache.mapper.weather.CityCacheMapper
import com.example.weather_app.cache.mapper.weather.CloudsCacheMapper
import com.example.weather_app.cache.mapper.weather.CoordinatesCacheMapper
import com.example.weather_app.cache.mapper.weather.CurrentWeatherCacheMapper
import com.example.weather_app.cache.mapper.weather.ForecastItemCacheMapper
import com.example.weather_app.cache.mapper.weather.ForecastWeatherCacheMapper
import com.example.weather_app.cache.mapper.weather.MainInfoCacheMapper
import com.example.weather_app.cache.mapper.place.PlaceCacheMapper
import com.example.weather_app.cache.mapper.weather.SysCacheMapper
import com.example.weather_app.cache.mapper.weather.WeatherCacheMapper
import com.example.weather_app.cache.mapper.weather.WindCacheMapper
import com.example.weather_app.cache.room.db.WeatherDatabase
import com.example.weather_app.cache.source.PlaceCacheDataSourceImpl
import com.example.weather_app.cache.source.WeatherCacheDataSourceImpl
import com.example.weather_app.core.Config
import com.example.weather_app.data.source.PlaceCacheDataSource
import com.example.weather_app.data.source.WeatherCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module {
    single { provideDatabase(androidContext()) }
    single { provideCurrentWeatherDao(get()) }
    single { provideForecastWeatherDao(get()) }
    single { providePlaceDao(get()) }
    single<WeatherCacheDataSource> { WeatherCacheDataSourceImpl(get(), get(), get(), get(), get()) }
    single<PlaceCacheDataSource> { PlaceCacheDataSourceImpl(get(), get()) }
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
    factory { PlaceCacheMapper() }
}

private fun provideDatabase(context: Context): WeatherDatabase {
    return Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        Config.ROOM_DATABASE_NAME
    ).build()
}

private fun provideCurrentWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.getCurrentWeatherDao()

private fun provideForecastWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.getForecastWeatherDao()

private fun providePlaceDao(weatherDatabase: WeatherDatabase) = weatherDatabase.getPlaceDao()