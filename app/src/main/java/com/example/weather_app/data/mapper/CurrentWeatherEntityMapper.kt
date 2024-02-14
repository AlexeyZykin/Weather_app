package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.CurrentWeatherEntity
import com.example.weather_app.domain.model.CurrentWeather

class CurrentWeatherEntityMapper(
    private val coordinatesEntityMapper: CoordinatesEntityMapper,
    private val weatherEntityMapper: WeatherEntityMapper,
    private val mainInfoEntityMapper: MainInfoEntityMapper,
    private val windEntityMapper: WindEntityMapper,
    private val sysEntityMapper: SysEntityMapper
) : Mapper<CurrentWeatherEntity, CurrentWeather>{
    override fun mapFromEntity(data: CurrentWeatherEntity): CurrentWeather {
        return CurrentWeather(
            id = data.id,
            coord = coordinatesEntityMapper.mapFromEntity(data.coord),
            weather = data.weather.map { weatherEntityMapper.mapFromEntity(it) },
            main = mainInfoEntityMapper.mapFromEntity(data.main),
            wind = windEntityMapper.mapFromEntity(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysEntityMapper.mapFromEntity(data.sys)
        )
    }

    override fun mapToEntity(data: CurrentWeather): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            id = data.id,
            coord = coordinatesEntityMapper.mapToEntity(data.coord),
            weather = data.weather.map { weatherEntityMapper.mapToEntity(it) },
            main = mainInfoEntityMapper.mapToEntity(data.main),
            wind = windEntityMapper.mapToEntity(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysEntityMapper.mapToEntity(data.sys)
        )
    }
}