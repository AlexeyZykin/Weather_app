package com.example.weather_app.cache.room.converter

import androidx.room.TypeConverter
import com.example.weather_app.cache.room.model.ForecastItemCache
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromForecastList(value: List<ForecastItemCache>): String = Gson().toJson(value)

    @TypeConverter
    fun toForecastList(value: String) = Gson().fromJson(value, Array<ForecastItemCache>::class.java).toList()
}