package com.example.weather_app.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.presentation.mapper.CurrentWeatherUiMapper
import com.example.weather_app.remote.model.CurrentWeatherResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchRealtimeWeatherUseCase: FetchRealtimeWeatherUseCase,
    private val currentWeatherUiMapper: CurrentWeatherUiMapper
) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherUiState>()
    val currentWeather: LiveData<CurrentWeatherUiState> get() = _currentWeather

    init {
        fetchRealtimeWeather()
    }

    fun fetchRealtimeWeather() = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke().distinctUntilChanged().collect {
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

}