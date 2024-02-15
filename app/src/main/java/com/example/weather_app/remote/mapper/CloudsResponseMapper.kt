package com.example.weather_app.remote.mapper

import com.example.weather_app.data.model.CloudsEntity
import com.example.weather_app.remote.model.CloudsResponse

class CloudsResponseMapper : Mapper<CloudsResponse, CloudsEntity> {
    override fun mapFromResponse(data: CloudsResponse): CloudsEntity {
        return CloudsEntity(data.all)
    }

    override fun mapToResponse(data: CloudsEntity): CloudsResponse {
        return CloudsResponse(data.all)
    }
}