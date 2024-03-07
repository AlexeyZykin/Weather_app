package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.ForecastWeather
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.ForecastWeatherUi

class ForecastWeatherUiMapper(
    private val forecastItemUiMapper: ForecastItemUiMapper,
    private val cityUiMapper: CityUiMapper
) : Mapper<ForecastWeatherUi, ForecastWeather> {
    override fun mapFromUi(data: ForecastWeatherUi): ForecastWeather {
        return ForecastWeather(
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemUiMapper.mapFromUi(it) },
            city = cityUiMapper.mapFromUi(data.city)
        )
    }

    override fun mapToUi(data: ForecastWeather): ForecastWeatherUi {
        return ForecastWeatherUi(
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemUiMapper.mapToUi(it) },
            city = cityUiMapper.mapToUi(data.city)
        )
    }
}