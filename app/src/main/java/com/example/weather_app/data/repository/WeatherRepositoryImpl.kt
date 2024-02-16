package com.example.weather_app.data.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.mapper.CurrentWeatherEntityMapper
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper
) : WeatherRepository {
    override suspend fun fetchRealtimeWeather(): Flow<Response<CurrentWeather>> = flow {
        emit(Response.Loading(isLoading = true))
        val response = try {
            weatherRemoteDataSource.fetchRealtimeWeather()
        }
        catch (e: Exception) {
            emit(Response.Error("No network available, please check your WiFi or Data connection"))
            null
        }
        catch (e: UnknownHostException) {
            emit(Response.Error(e.message ?: "Error"))
            null
        }

        response?.let {
            emit(Response.Success(currentWeatherEntityMapper.mapFromEntity(it)))
            emit(Response.Loading(false))
        }
    }
}