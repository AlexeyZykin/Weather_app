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
    private val _forecastWeather = MutableLiveData<ForecastUiState>()
    val forecastWeather: LiveData<ForecastUiState> get() = _forecastWeather
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
            Log.d("viewModel", it.toString())
            when (it) {
                is Response.Loading -> if (it.isLoading) _forecastWeather.postValue(ForecastUiState.Loading)

                is Response.Success -> _forecastWeather.postValue(
                    ForecastUiState.Success(forecastWeatherUiMapper.mapToUi(it.data))
                )

                is Response.Error -> _forecastWeather.postValue(ForecastUiState.Error(it.msg))
            }
        }
    }

}