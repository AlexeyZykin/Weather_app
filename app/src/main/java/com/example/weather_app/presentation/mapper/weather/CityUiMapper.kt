package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.City
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.CityUi

class CityUiMapper(private val coordinatesUiMapper: CoordinatesUiMapper) : Mapper<CityUi, City> {
    override fun mapFromUi(data: CityUi): City {
        return City(
            id = data.id,
            name = data.name,
            coord = coordinatesUiMapper.mapFromUi(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

    override fun mapToUi(data: City): CityUi {
        return CityUi(
            id = data.id,
            name = data.name,
            coord = coordinatesUiMapper.mapToUi(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }
}