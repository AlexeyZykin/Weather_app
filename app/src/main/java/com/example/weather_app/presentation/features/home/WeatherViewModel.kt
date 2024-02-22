package com.example.weather_app.presentation.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.FetchForecastUseCase
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.presentation.mapper.CurrentWeatherUiMapper
import com.example.weather_app.presentation.mapper.ForecastWeatherUiMapper
import com.example.weather_app.presentation.model.ForecastItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchRealtimeWeatherUseCase: FetchRealtimeWeatherUseCase,
    private val fetchForecastUseCase: FetchForecastUseCase,
    private val currentWeatherUiMapper: CurrentWeatherUiMapper,
    private val forecastWeatherUiMapper: ForecastWeatherUiMapper
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherUiState>()
    val currentWeather: LiveData<CurrentWeatherUiState> get() = _currentWeather
    private val _hourlyForecast = MutableLiveData<ForecastUiState>()
    val hourlyForecast: LiveData<ForecastUiState> get() = _hourlyForecast
    private val _dailyForecast = MutableLiveData<ForecastUiState>()
    val dailyForecast: LiveData<ForecastUiState> get() = _dailyForecast

    fun fetchRealtimeWeather(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> if (it.isLoading) _currentWeather.postValue(
                    CurrentWeatherUiState.Loading
                )

                is Response.Success -> _currentWeather.postValue(
                    CurrentWeatherUiState.Success(currentWeatherUiMapper.mapToUi(it.data))
                )

                is Response.Error -> _currentWeather.postValue(
                    CurrentWeatherUiState.Error(it.msg)
                )
            }
        }
    }

    fun fetchForecast(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> if (it.isLoading) {
                    _hourlyForecast.postValue(ForecastUiState.Loading)
                    _dailyForecast.postValue(ForecastUiState.Loading)
                }

                is Response.Success -> {
                    mapToHourlyForecast(forecastWeatherUiMapper.mapToUi(it.data).forecastList)
                    mapToDailyForecast(forecastWeatherUiMapper.mapToUi(it.data).forecastList)
                }

                is Response.Error -> {
                    _hourlyForecast.postValue(ForecastUiState.Error(it.msg))
                    _dailyForecast.postValue(ForecastUiState.Error(it.msg))
                }
            }
        }
    }

    private fun mapToHourlyForecast(forecast: List<ForecastItemUi>) {
        _hourlyForecast.postValue(ForecastUiState.Success(forecast.take(8)))
    }

    private fun mapToDailyForecast(forecast: List<ForecastItemUi>) {
        _dailyForecast.postValue(ForecastUiState.Success(
            forecast.filterIndexed { index, _ ->
                index % 8 == 0
            }
        ))
    }
}