package com.example.weather_app.data.mapper

import com.example.weather_app.data.model.CloudsEntity
import com.example.weather_app.domain.model.Clouds

class CloudsEntityMapper : Mapper<CloudsEntity, Clouds> {
    override fun mapFromEntity(data: CloudsEntity): Clouds {
        return Clouds(data.all)
    }

    override fun mapToEntity(data: Clouds): CloudsEntity {
        return CloudsEntity(data.all)
    }

}