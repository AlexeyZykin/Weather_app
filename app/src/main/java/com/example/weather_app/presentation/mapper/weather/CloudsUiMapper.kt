package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.Clouds
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.CloudsUi

class CloudsUiMapper : Mapper<CloudsUi, Clouds> {
    override fun mapFromUi(data: CloudsUi) = Clouds(data.all)
    override fun mapToUi(data: Clouds) = CloudsUi(data.all)
}