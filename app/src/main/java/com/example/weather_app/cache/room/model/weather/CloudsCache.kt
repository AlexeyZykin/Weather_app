package com.example.weather_app.cache.room.model.weather

import androidx.room.ColumnInfo

data class CloudsCache(@ColumnInfo("cloud_cover") val all: Int)
