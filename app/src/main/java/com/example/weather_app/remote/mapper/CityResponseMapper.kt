package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.CityEntity
import com.example.weather_app.remote.model.CityResponse

class CityResponseMapper(private val coordinatesResponseMapper: CoordinatesResponseMapper) : Mapper<CityResponse, CityEntity> {
    override fun mapFromResponse(data: CityResponse): CityEntity {
        return CityEntity(
            id = data.id,
            name = data.name,
            coord = coordinatesResponseMapper.mapFromResponse(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

    override fun mapToResponse(data: CityEntity): CityResponse {
        return CityResponse(
            id = data.id,
            name = data.name,
            coord = coordinatesResponseMapper.mapToResponse(data.coord),
            country = data.country,
            population = data.population,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }
}