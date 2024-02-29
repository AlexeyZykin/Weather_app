package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.weather.ForecastItemEntity
import com.example.weather_app.domain.model.weather.ForecastItem

class ForecastItemEntityMapper(
    private val mainInfoEntityMapper: MainInfoEntityMapper,
    private val weatherEntityMapper: WeatherEntityMapper,
    private val cloudsEntityMapper: CloudsEntityMapper,
    private val windEntityMapper: WindEntityMapper
) : Mapper<ForecastItemEntity, ForecastItem> {
    override fun mapFromEntity(data: ForecastItemEntity): ForecastItem {
        return ForecastItem(
            dt = data.dt,
            mainInfo = mainInfoEntityMapper.mapFromEntity(data.mainInfo),
            weather = weatherEntityMapper.mapFromEntity(data.weather),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsEntityMapper.mapFromEntity(data.clouds),
            wind = windEntityMapper.mapFromEntity(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }

    override fun mapToEntity(data: ForecastItem): ForecastItemEntity {
        return ForecastItemEntity(
            dt = data.dt,
            mainInfo = mainInfoEntityMapper.mapToEntity(data.mainInfo),
            weather = weatherEntityMapper.mapToEntity(data.weather),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsEntityMapper.mapToEntity(data.clouds),
            wind = windEntityMapper.mapToEntity(data.wind),
            partOfDay = data.partOfDay,
            dtTxt = data.dtTxt
        )
    }

}