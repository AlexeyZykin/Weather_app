package com.example.weather_app.remote.api

import com.example.weather_app.remote.constants.ApiConstants
import com.example.weather_app.remote.model.RealtimeWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getRealtimeWeather(
        @Query("lat") lat: Double=0.0,
        @Query("lot") lot: Double=0.0,
        @Query("q") q: String = "Moscow",
        @Query("units") unit: String = ApiConstants.UNIT_MEASURE,
        @Query("appid") apiKey: String = ApiConstants.API_KEY
        ): RealtimeWeatherResponse
}