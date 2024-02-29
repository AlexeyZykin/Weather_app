package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.weather.CloudsEntity
import com.example.weather_app.domain.model.weather.Clouds

class CloudsEntityMapper : Mapper<CloudsEntity, Clouds> {
    override fun mapFromEntity(data: CloudsEntity) = Clouds(data.all)
    override fun mapToEntity(data: Clouds) = CloudsEntity(data.all)
}