package com.example.weather_app.remote.api

import com.example.weather_app.remote.constants.ApiConstants
import com.example.weather_app.remote.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getRealtimeWeather(
        @Query("q") q: String = "Novosibirsk",
        @Query("units") unit: String = ApiConstants.UNIT_MEASURE,
        @Query("appid") apiKey: String = ApiConstants.API_KEY
        ): CurrentWeatherResponse
}