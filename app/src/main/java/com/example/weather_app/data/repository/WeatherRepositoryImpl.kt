package com.example.weather_app.data.repository

import android.util.Log
import com.example.weather_app.core.Response
import com.example.weather_app.data.mapper.CurrentWeatherEntityMapper
import com.example.weather_app.data.mapper.ForecastWeatherEntityMapper
import com.example.weather_app.data.source.WeatherCacheDataSource
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.model.ForecastWeather
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherCacheDataSource: WeatherCacheDataSource,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper,
    private val forecastWeatherEntityMapper: ForecastWeatherEntityMapper
) : WeatherRepository {
    override suspend fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>> = flow {
        emit(Response.Loading(isLoading = true))
        if (weatherCacheDataSource.isCachedCurrentWeather())
            emit(Response.Success(currentWeatherEntityMapper.mapFromEntity(
                weatherCacheDataSource.getCurrentWeatherFromCache())))

        val response = try {
            weatherRemoteDataSource.fetchRealtimeWeather(lat, lon)
        } catch (e: Exception) {
            emit(Response.Error("No network available, please check your WiFi or Data connection"))
            null
        } catch (e: UnknownHostException) {
            emit(Response.Error(e.message ?: "Error"))
            null
        }
        if (response != null) {
            weatherCacheDataSource.clearCacheCurrentWeather()
            weatherCacheDataSource.addCurrentWeatherToCache(response)
            emit(Response.Success(
                    currentWeatherEntityMapper.mapFromEntity(
                        weatherCacheDataSource.getCurrentWeatherFromCache())))
            emit(Response.Loading(false))
        }
    }

    override suspend fun fetchForecast(
        lat: Double,
        lon: Double
    ): Flow<Response<ForecastWeather>> = flow {
        emit(Response.Loading(isLoading = true))
        if (weatherCacheDataSource.isCachedForecast())
            emit(Response.Success(forecastWeatherEntityMapper.mapFromEntity(
                weatherCacheDataSource.getForecastWeatherFromCache())))

        val response = try {
            weatherRemoteDataSource.fetchForecast(lat, lon)
        } catch (e: Exception) {
            emit(Response.Error("No network available, please check your WiFi or Data connection"))
            null
        }

        if (response != null) {
            weatherCacheDataSource.clearCacheForecastWeather()
            weatherCacheDataSource.addForecastWeatherToCache(response)
            emit(Response.Success(
                forecastWeatherEntityMapper.mapFromEntity(
                    weatherCacheDataSource.getForecastWeatherFromCache())))
            emit(Response.Loading(false))
        }
    }
}