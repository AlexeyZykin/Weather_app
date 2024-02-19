package com.example.weather_app.remote.api

import com.example.weather_app.BuildConfig
import com.example.weather_app.core.Constants

import com.example.weather_app.remote.model.CurrentWeatherResponse
import com.example.weather_app.remote.model.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getRealtimeWeather(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("q") cityName: String = "",
        @Query("units") unit: String = Constants.UNIT_MEASURE,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
        ): CurrentWeatherResponse


    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("units") unit: String = Constants.UNIT_MEASURE,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): ForecastWeatherResponse
}