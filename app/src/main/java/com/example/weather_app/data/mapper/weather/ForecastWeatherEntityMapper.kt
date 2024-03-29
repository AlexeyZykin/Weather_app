package com.example.weather_app.data.mapper.weather

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.weather.ForecastWeatherEntity
import com.example.weather_app.domain.model.weather.ForecastWeather

class ForecastWeatherEntityMapper(
    private val forecastItemEntityMapper: ForecastItemEntityMapper,
    private val cityEntityMapper: CityEntityMapper
) : Mapper<ForecastWeatherEntity, ForecastWeather> {
    override fun mapFromEntity(data: ForecastWeatherEntity): ForecastWeather {
        return ForecastWeather(
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemEntityMapper.mapFromEntity(it) },
            city = cityEntityMapper.mapFromEntity(data.city)
        )
    }

    override fun mapToEntity(data: ForecastWeather): ForecastWeatherEntity {
        return ForecastWeatherEntity(
            cnt = data.cnt,
            forecastList = data.forecastList.map { forecastItemEntityMapper.mapToEntity(it) },
            city = cityEntityMapper.mapToEntity(data.city)
        )
    }
}