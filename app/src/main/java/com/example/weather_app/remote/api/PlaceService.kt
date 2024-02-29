package com.example.weather_app.remote.api

import com.example.weather_app.BuildConfig
import com.example.weather_app.remote.model.place.AutocompletePlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("autocomplete")
    suspend fun fetchAutoCompletePlaces(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY_PLACE_AUTOCOMPLETE,
        @Query("type") type: String = "city",
        @Query("format") format: String = "json",
        @Query("text") inputText: String
    ) : AutocompletePlaceResponse
}