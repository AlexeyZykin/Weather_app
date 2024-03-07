package com.example.weather_app.data.mapper.weather

import com.example.weather_app.data.mapper.Mapper
import com.example.weather_app.data.model.weather.WeatherEntity
import com.example.weather_app.domain.model.weather.Weather


class WeatherEntityMapper : Mapper<WeatherEntity, Weather> {
    override fun mapFromEntity(data: WeatherEntity): Weather {
        return Weather(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )
    }

    override fun mapToEntity(data: Weather): WeatherEntity {
        return WeatherEntity(
            id = data.id,
            main = data.main,
            description = data.description,
            icon = data.icon
        )
    }

}