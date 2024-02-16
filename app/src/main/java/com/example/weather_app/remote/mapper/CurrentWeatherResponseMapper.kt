package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.remote.model.CurrentWeatherResponse

class CurrentWeatherResponseMapper(
    private val coordinatesResponseMapper: CoordinatesResponseMapper,
    private val weatherResponseMapper: WeatherResponseMapper,
    private val mainInfoResponseMapper: MainInfoResponseMapper,
    private val windResponseMapper: WindResponseMapper,
    private val cloudsResponseMapper: CloudsResponseMapper,
    private val sysResponseMapper: SysResponseMapper

) : Mapper<CurrentWeatherResponse, CurrentWeatherEntity> {
    override fun mapFromResponse(data: CurrentWeatherResponse): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            id = data.id,
            coord = coordinatesResponseMapper.mapFromResponse(data.coord),
            weather = data.weather.map { weatherResponseMapper.mapFromResponse(it) }.first(),
            main = mainInfoResponseMapper.mapFromResponse(data.main),
            wind = windResponseMapper.mapFromResponse(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysResponseMapper.mapFromResponse(data.sys),
            visibility = data.visibility,
            clouds = cloudsResponseMapper.mapFromResponse(data.clouds)
        )
    }

    override fun mapToResponse(data: CurrentWeatherEntity): CurrentWeatherResponse {
        return CurrentWeatherResponse(
            id = data.id,
            coord = coordinatesResponseMapper.mapToResponse(data.coord),
            weather = listOf(weatherResponseMapper.mapToResponse(data.weather)),
            main = mainInfoResponseMapper.mapToResponse(data.main),
            wind = windResponseMapper.mapToResponse(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysResponseMapper.mapToResponse(data.sys),
            visibility = data.visibility,
            clouds = cloudsResponseMapper.mapToResponse(data.clouds)
        )
    }

}