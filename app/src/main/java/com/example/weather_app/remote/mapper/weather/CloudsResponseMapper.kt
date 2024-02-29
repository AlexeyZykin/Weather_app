package com.example.weather_app.remote.mapper.weather

import com.example.weather_app.data.model.weather.CloudsEntity
import com.example.weather_app.remote.mapper.Mapper
import com.example.weather_app.remote.model.weather.CloudsResponse

class CloudsResponseMapper : Mapper<CloudsResponse, CloudsEntity> {
    override fun mapFromResponse(data: CloudsResponse) = CloudsEntity(data.all)
    override fun mapToResponse(data: CloudsEntity) = CloudsResponse(data.all)
}