package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.ForecastItemEntity
import com.example.weather_app.remote.model.ForecastItemResponse
import com.example.weather_app.remote.model.SysForecastResponse

class ForecastItemResponseMapper(
    private val mainInfoResponseMapper: MainInfoResponseMapper,
    private val weatherResponseMapper: WeatherResponseMapper,
    private val cloudsResponseMapper: CloudsResponseMapper,
    private val windResponseMapper: WindResponseMapper
    ) : Mapper<ForecastItemResponse, ForecastItemEntity>{
    override fun mapFromResponse(data: ForecastItemResponse): ForecastItemEntity {
        return ForecastItemEntity(
            dt = data.dt,
            mainInfo = mainInfoResponseMapper.mapFromResponse(data.main),
            weather = data.weather.map { weatherResponseMapper.mapFromResponse(it) }.first(),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsResponseMapper.mapFromResponse(data.clouds),
            wind = windResponseMapper.mapFromResponse(data.wind),
            partOfDay = data.sys.pod,
            dtTxt = data.dt_txt
        )
    }

    override fun mapToResponse(data: ForecastItemEntity): ForecastItemResponse {
        return ForecastItemResponse(
            dt = data.dt,
            main = mainInfoResponseMapper.mapToResponse(data.mainInfo),
            weather = listOf(weatherResponseMapper.mapToResponse(data.weather)),
            visibility = data.visibility,
            pop = data.pop,
            clouds = cloudsResponseMapper.mapToResponse(data.clouds),
            wind = windResponseMapper.mapToResponse(data.wind),
            sys = SysForecastResponse(data.partOfDay),
            dt_txt = data.dtTxt
        )
    }
}