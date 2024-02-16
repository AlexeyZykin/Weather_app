package com.example.weather_app.cache.mapper

interface Mapper<Cache, Entity> {
    fun mapFromCache(data: Cache): Entity
    fun mapToCache(data: Entity): Cache
}