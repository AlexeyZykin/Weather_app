package com.example.weather_app.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.FetchForecastUseCase
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.presentation.utils.UiState
import com.example.weather_app.presentation.mapper.CurrentWeatherUiMapper
import com.example.weather_app.presentation.mapper.ForecastWeatherUiMapper
import com.example.weather_app.presentation.model.CurrentWeatherUi
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

    private val _currentWeather = MutableLiveData<UiState<CurrentWeatherUi>>()
    val currentWeather: LiveData<UiState<CurrentWeatherUi>> get() = _currentWeather
    private val _hourlyForecast = MutableLiveData<UiState<List<ForecastItemUi>>>()
    val hourlyForecast: LiveData<UiState<List<ForecastItemUi>>> get() = _hourlyForecast
    private val _dailyForecast = MutableLiveData<UiState<List<ForecastItemUi>>>()
    val dailyForecast: LiveData<UiState<List<ForecastItemUi>>> get() = _dailyForecast

    fun fetchRealtimeWeather(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> if (it.isLoading) _currentWeather.postValue(
                    UiState.Loading(isLoading = true)
                )
                else _currentWeather.postValue(UiState.Loading(isLoading = false))

                is Response.Success -> if (it.data != null) _currentWeather.postValue(
                    UiState.Success(currentWeatherUiMapper.mapToUi(it.data))
                )

                is Response.Error -> _currentWeather.postValue(
                    UiState.Error(it.msg)
                )
            }
        }
    }

    fun fetchForecast(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> if (it.isLoading) {
                    _hourlyForecast.postValue(UiState.Loading(isLoading = true))
                    _dailyForecast.postValue(UiState.Loading(isLoading = true))
                }
                else {
                    _hourlyForecast.postValue(UiState.Loading(isLoading = false))
                    _dailyForecast.postValue(UiState.Loading(isLoading = false))
                }

                is Response.Success -> if (it.data != null) {
                    mapToHourlyForecast(forecastWeatherUiMapper.mapToUi(it.data).forecastList)
                    mapToDailyForecast(forecastWeatherUiMapper.mapToUi(it.data).forecastList)
                }

                is Response.Error -> {
                    _hourlyForecast.postValue(UiState.Error(it.msg))
                    _dailyForecast.postValue(UiState.Error(it.msg))
                }
            }
        }
    }

    private fun mapToHourlyForecast(forecast: List<ForecastItemUi>) {
        _hourlyForecast.postValue(UiState.Success(forecast.take(8)))
    }

    private fun mapToDailyForecast(forecast: List<ForecastItemUi>) {
        _dailyForecast.postValue(
            UiState.Success(
            forecast.filterIndexed { index, _ ->
                index % 8 == 0
            }
        ))
    }
}