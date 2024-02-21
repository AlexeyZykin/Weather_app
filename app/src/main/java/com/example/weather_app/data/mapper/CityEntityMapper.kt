package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.CityEntity
import com.example.weather_app.domain.model.City

class CityEntityMapper(private val coordinatesEntityMapper: CoordinatesEntityMapper) : Mapper<CityEntity, City> {
    override fun mapFromEntity(data: CityEntity): City {
        return City(
            id = data.id,
            name = data.name,
            coord = coordinatesEntityMapper.mapFromEntity(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

    override fun mapToEntity(data: City): CityEntity {
        return CityEntity(
            id = data.id,
            name = data.name,
            coord = coordinatesEntityMapper.mapToEntity(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }
}