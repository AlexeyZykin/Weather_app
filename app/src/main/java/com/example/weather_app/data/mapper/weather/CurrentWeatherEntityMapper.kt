package com.example.weather_app.data.mapper.weather

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.weather.CurrentWeatherEntity
import com.example.weather_app.domain.model.weather.CurrentWeather

class CurrentWeatherEntityMapper(
    private val coordinatesEntityMapper: CoordinatesEntityMapper,
    private val weatherEntityMapper: WeatherEntityMapper,
    private val mainInfoEntityMapper: MainInfoEntityMapper,
    private val windEntityMapper: WindEntityMapper,
    private val cloudsEntityMapper: CloudsEntityMapper,
    private val sysEntityMapper: SysEntityMapper
) : Mapper<CurrentWeatherEntity, CurrentWeather> {
    override fun mapFromEntity(data: CurrentWeatherEntity): CurrentWeather {
        return CurrentWeather(
            id = data.id,
            coord = coordinatesEntityMapper.mapFromEntity(data.coord),
            weather = weatherEntityMapper.mapFromEntity(data.weather),
            main = mainInfoEntityMapper.mapFromEntity(data.main),
            wind = windEntityMapper.mapFromEntity(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysEntityMapper.mapFromEntity(data.sys),
            visibility = data.visibility,
            clouds = cloudsEntityMapper.mapFromEntity(data.clouds)
        )
    }

    override fun mapToEntity(data: CurrentWeather): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            id = data.id,
            coord = coordinatesEntityMapper.mapToEntity(data.coord),
            weather = weatherEntityMapper.mapToEntity(data.weather),
            main = mainInfoEntityMapper.mapToEntity(data.main),
            wind = windEntityMapper.mapToEntity(data.wind),
            dt = data.dt,
            name = data.name,
            sys = sysEntityMapper.mapToEntity(data.sys),
            visibility = data.visibility,
            clouds = cloudsEntityMapper.mapToEntity(data.clouds)
        )
    }
}