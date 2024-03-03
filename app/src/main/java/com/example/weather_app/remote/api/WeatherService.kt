package com.example.weather_app.remote.api

import com.example.weather_app.BuildConfig
import com.example.weather_app.core.Config

import com.example.weather_app.remote.model.weather.CurrentWeatherResponse
import com.example.weather_app.remote.model.weather.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getRealtimeWeather(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("q") cityName: String = "",
        @Query("units") unit: String = Config.UNIT_MEASURE,
        @Query("appid") apiKey: String = BuildConfig.API_KEY_WEATHER
        ): CurrentWeatherResponse


    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("units") unit: String = Config.UNIT_MEASURE,
        @Query("appid") apiKey: String = BuildConfig.API_KEY_WEATHER
    ): ForecastWeatherResponse
}