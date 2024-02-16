package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.Clouds
import com.example.weather_app.presentation.model.CloudsUi

class CloudsUiMapper : Mapper<CloudsUi, Clouds>{
    override fun mapFromUi(data: CloudsUi): Clouds {
        return Clouds(data.all)
    }

    override fun mapToUi(data: Clouds): CloudsUi {
        return CloudsUi(data.all)
    }
}