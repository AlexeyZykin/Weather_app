package com.example.weather_app.presentation.mapper

interface Mapper<Ui, Model> {
    fun mapFromUi(data: Ui): Model
    fun mapToUi(data: Model): Ui
}