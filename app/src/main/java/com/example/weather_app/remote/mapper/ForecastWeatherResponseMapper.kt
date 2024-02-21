package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.ForecastWeatherEntity
import com.example.weather_app.remote.model.ForecastWeatherResponse

class ForecastWeatherResponseMapper(
    private val forecastItemResponseMapper: ForecastItemResponseMapper,
    private val cityResponseMapper: CityResponseMapper
) : Mapper<ForecastWeatherResponse, ForecastWeatherEntity> {
    override fun mapFromResponse(data: ForecastWeatherResponse): ForecastWeatherEntity {
        return ForecastWeatherEntity(
            cnt = data.cnt,
            forecastList = data.list.map { forecastItemResponseMapper.mapFromResponse(it) },
            city = cityResponseMapper.mapFromResponse(data.city)
        )
    }

    override fun mapToResponse(data: ForecastWeatherEntity): ForecastWeatherResponse {
        return ForecastWeatherResponse(
            cnt = data.cnt,
            list = data.forecastList.map { forecastItemResponseMapper.mapToResponse(it) },
            city = cityResponseMapper.mapToResponse(data.city)
        )
    }
}