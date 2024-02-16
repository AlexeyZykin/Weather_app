package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.presentation.model.CurrentWeatherUi

class CurrentWeatherUiMapper(
    private val coordinatesUiMapper: CoordinatesUiMapper,
    private val weatherUiMapper: WeatherUiMapper,
    private val mainInfoUiMapper: MainInfoUiMapper,
    private val windUiMapper: WindUiMapper,
    private val cloudsUiMapper: CloudsUiMapper,
    private val sysUiMapper: SysUiMapper
) : Mapper<CurrentWeatherUi, CurrentWeather> {
    override fun mapFromUi(data: CurrentWeatherUi): CurrentWeather {
        return CurrentWeather(
            id = data.id,
            coord = coordinatesUiMapper.mapFromUi(data.coord),
            weather = data.weather.map { weatherUiMapper.mapFromUi(it) },
            main = mainInfoUiMapper.mapFromUi(data.main),
            visibility = data.visibility,
            wind = windUiMapper.mapFromUi(data.wind),
            clouds = cloudsUiMapper.mapFromUi(data.clouds),
            dt = data.dt,
            name = data.name,
            sys = sysUiMapper.mapFromUi(data.sys)
        )
    }

    override fun mapToUi(data: CurrentWeather): CurrentWeatherUi {
        return CurrentWeatherUi(
            id = data.id,
            coord = coordinatesUiMapper.mapToUi(data.coord),
            weather = data.weather.map { weatherUiMapper.mapToUi(it) },
            main = mainInfoUiMapper.mapToUi(data.main),
            visibility = data.visibility,
            wind = windUiMapper.mapToUi(data.wind),
            clouds = cloudsUiMapper.mapToUi(data.clouds),
            dt = data.dt,
            name = data.name,
            sys = sysUiMapper.mapToUi(data.sys)
        )
    }
}