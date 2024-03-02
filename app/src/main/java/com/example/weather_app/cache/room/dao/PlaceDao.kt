package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.cache.room.model.PlaceCache
import com.example.weather_app.core.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM ${Constants.ROOM_PLACE_TABLE_NAME}")
    fun getPlaces(): Flow<List<PlaceCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlace(place: PlaceCache)

    @Query("DELETE FROM ${Constants.ROOM_PLACE_TABLE_NAME} WHERE isCurrentPlace = 0")
    suspend fun deleteAllPlaces()

    @Query("DELETE FROM ${Constants.ROOM_PLACE_TABLE_NAME} WHERE id = :id")
    suspend fun deletePlace(id: Int)

    @Query("SELECT (SELECT COUNT(*) FROM ${Constants.ROOM_PLACE_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean
}