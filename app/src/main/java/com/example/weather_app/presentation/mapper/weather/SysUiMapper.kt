package com.example.weather_app.presentation.mapper.weather

import com.example.weather_app.domain.model.weather.Sys
import com.example.weather_app.presentation.mapper.Mapper
import com.example.weather_app.presentation.model.weather.SysUi

class SysUiMapper : Mapper<SysUi, Sys> {
    override fun mapFromUi(data: SysUi) = Sys(data.sunrise, data.sunset)
    override fun mapToUi(data: Sys) = SysUi(data.sunrise, data.sunset)
}