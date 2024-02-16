package com.example.weather_app.presentation.mapper

import com.example.weather_app.domain.model.Sys
import com.example.weather_app.presentation.model.SysUi

class SysUiMapper : Mapper<SysUi, Sys> {
    override fun mapFromUi(data: SysUi): Sys {
        return Sys(data.sunrise, data.sunset)
    }

    override fun mapToUi(data: Sys): SysUi {
        return SysUi(data.sunrise, data.sunset)
    }
}