package com.example.weather_app.data.mapper

interface Mapper<Entity, Model> {
    fun mapFromEntity(data: Entity): Model
    fun mapToEntity(data: Model): Entity
}