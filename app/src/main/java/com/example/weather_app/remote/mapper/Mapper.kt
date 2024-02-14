package com.example.weather_app.remote.mapper

interface Mapper<Response, Entity> {
    fun mapFromResponse(data: Response): Entity
    fun mapToResponse(data: Entity): Response
}