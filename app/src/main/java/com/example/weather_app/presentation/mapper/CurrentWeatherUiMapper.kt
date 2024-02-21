package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.presentation.model.CurrentWeatherUi
import com.example.weather_app.presentation.model.WeatherType

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
            weather = weatherUiMapper.mapFromUi(data.weather),
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
            weather = weatherUiMapper.mapToUi(data.weather),
            main = mainInfoUiMapper.mapToUi(data.main),
            visibility = data.visibility,
            wind = windUiMapper.mapToUi(data.wind),
            clouds = cloudsUiMapper.mapToUi(data.clouds),
            dt = data.dt,
            name = data.name,
            sys = sysUiMapper.mapToUi(data.sys),
            weatherType = mapIdToWeatherType(data.weather.id)
        )
    }
}