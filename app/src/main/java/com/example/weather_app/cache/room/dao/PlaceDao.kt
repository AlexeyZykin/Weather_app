package com.example.weather_app.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weather_app.cache.room.model.PlaceCache
import com.example.weather_app.core.Config
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM ${Config.ROOM_PLACE_TABLE_NAME}")
    fun getPlaces(): Flow<List<PlaceCache>>

    @Query("SELECT * FROM ${Config.ROOM_PLACE_TABLE_NAME} WHERE city = :city")
    suspend fun getPlaceByCity(city: String): PlaceCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlace(place: PlaceCache)

    @Query("DELETE FROM ${Config.ROOM_PLACE_TABLE_NAME} WHERE city != :currentCity")
    suspend fun deleteAllPlacesExceptCurrent(currentCity: String)

    @Query("DELETE FROM ${Config.ROOM_PLACE_TABLE_NAME} WHERE id = :id")
    suspend fun deletePlace(id: Int)

    @Query("SELECT (SELECT COUNT(*) FROM ${Config.ROOM_PLACE_TABLE_NAME}) != 0")
    suspend fun isNotEmpty(): Boolean

    @Update
    suspend fun updatePlace(place: PlaceCache)
}