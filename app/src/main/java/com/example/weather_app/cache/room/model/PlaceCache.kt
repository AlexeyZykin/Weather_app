package com.example.weather_app.cache.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather_app.core.Config

@Entity(tableName = Config.ROOM_PLACE_TABLE_NAME)
data class PlaceCache(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("city") val city: String,
    @ColumnInfo("lon") val lon: Double,
    @ColumnInfo("lat") val lat: Double,
    @ColumnInfo("place_id_str") val placeIdStr: String
)