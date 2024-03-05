package com.example.weather_app.data.repository

import com.example.weather_app.core.Response
import com.example.weather_app.data.mapper.CurrentWeatherEntityMapper
import com.example.weather_app.data.mapper.ForecastItemEntityMapper
import com.example.weather_app.data.mapper.ForecastWeatherEntityMapper
import com.example.weather_app.data.source.WeatherCacheDataSource
import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.domain.model.weather.CurrentWeather
import com.example.weather_app.domain.model.weather.ForecastItem
import com.example.weather_app.domain.model.weather.ForecastWeather
import com.example.weather_app.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flatten
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import java.net.UnknownHostException


class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherCacheDataSource: WeatherCacheDataSource,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper,
    private val forecastWeatherEntityMapper: ForecastWeatherEntityMapper,
    private val forecastItemEntityMapper: ForecastItemEntityMapper
) : WeatherRepository {

    override fun fetchRealtimeWeather(lat: Double, lon: Double): Flow<Response<CurrentWeather>> = flow {
        emit(Response.Loading())
        if (weatherCacheDataSource.isCachedCurrentWeather())
            emit(Response.Success(currentWeatherEntityMapper.mapFromEntity(
                weatherCacheDataSource.getCurrentWeather())))

        val response = try {
            emit(Response.Loading())
            weatherRemoteDataSource.fetchRealtimeWeather(lat, lon)
        } catch (e: Exception) {
            emit(Response.Error("No network available, please check your WiFi or Data connection"))
            null
        }

        if (response != null) {
            weatherCacheDataSource.clearCacheCurrentWeather()
            weatherCacheDataSource.addCurrentWeatherToCache(response)
            emit(Response.Success(
                    currentWeatherEntityMapper.mapFromEntity(
                        weatherCacheDataSource.getCurrentWeather())))
        }
    }

    override fun fetchForecast(
        lat: Double,
        lon: Double
    ): Flow<Response<ForecastWeather>> = flow {
        emit(Response.Loading())
        if (weatherCacheDataSource.isCachedForecast())
            emit(
                Response.Success(
                    forecastWeatherEntityMapper.mapFromEntity(
                        weatherCacheDataSource.getForecastWeatherFromCache()
                    )
                )
            )

        val response = try {
            emit(Response.Loading())
            weatherRemoteDataSource.fetchForecast(lat, lon)
        } catch (e: Exception) {
            emit(Response.Error("No network available, please check your WiFi or Data connection"))
            null
        }

        if (response != null) {
            weatherCacheDataSource.clearCacheForecastWeather()
            weatherCacheDataSource.addForecastWeatherToCache(response)
            emit(
                Response.Success(
                    forecastWeatherEntityMapper.mapFromEntity(
                        weatherCacheDataSource.getForecastWeatherFromCache()
                    )
                )
            )
        }
    }

    override fun fetchForecastByTime(dt: Long): Flow<Response<ForecastItem>> = flow {
        emit(Response.Loading())

        val cachedData = try {
            weatherCacheDataSource.getForecastByTime(dt)
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error"))
            null
        }

        if (cachedData != null)
            emit(Response.Success(forecastItemEntityMapper.mapFromEntity(cachedData)))

    }

    override fun fetchForecastByDay(dtTxt: String): Flow<Response<List<ForecastItem>>> =
        flow {
            emit(Response.Loading())
            val cachedData = try {
                weatherCacheDataSource.getForecastByDay(dtTxt)
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "Error"))
                null
            }

            if (cachedData != null)
                emit(Response.Success(cachedData.map { forecastItemEntityMapper.mapFromEntity(it) }))

        }
}