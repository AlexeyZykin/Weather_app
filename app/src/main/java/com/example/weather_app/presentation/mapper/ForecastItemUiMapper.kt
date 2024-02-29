package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.weather.ForecastItem
import com.example.weather_app.presentation.model.ForecastItemUi

class ForecastItemUiMapper(
    private val mainInfoUiMapper: MainInfoUiMapper,
    private val weatherUiMapper: WeatherUiMapper,
    private val cloudsUiMapper: CloudsUiMapper,
    private val windUiMapper: WindUiMapper
) : Mapper<ForecastItemUi, ForecastItem> {
    override fun mapFromUi(data: ForecastItemUi): ForecastItem {
        return ForecastItem(
            dt = data.dt,
            mainInfo = mainInfoUiMapper.mapFromUi(data.mainInfo),
            weather = weatherUiMapper.mapFromUi(data.weather),
            visibility = data.visibility,
            pop = (data.probabilityOfPrecipitation / 100).toDouble(),
            clouds = cloudsUiMapper.mapFromUi(data.clouds),
            wind = windUiMapper.mapFromUi(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }

    override fun mapToUi(data: ForecastItem): ForecastItemUi {
        return ForecastItemUi(
            dt = data.dt,
            mainInfo = mainInfoUiMapper.mapToUi(data.mainInfo),
            weather = weatherUiMapper.mapToUi(data.weather),
            weatherType = mapIdToWeatherType(data.weather.id),
            visibility = data.visibility,
            probabilityOfPrecipitation = (data.pop * 100).toInt(),
            clouds = cloudsUiMapper.mapToUi(data.clouds),
            wind = windUiMapper.mapToUi(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }
}