package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.Weather
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.WeatherUi

class WeatherUiMapper : Mapper<WeatherUi, Weather> {
    override fun mapFromUi(data: WeatherUi): Weather {
        return Weather(
            data.id,
            data.main,
            data.description,
            data.icon
        )
    }

    override fun mapToUi(data: Weather): WeatherUi {
        return WeatherUi(
            data.id,
            data.main,
            data.description,
            data.icon
        )
    }
}