package com.example.weather_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.domain.model.CurrentWeather
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.remote.model.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchRealtimeWeatherUseCase: FetchRealtimeWeatherUseCase
) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> get() = _currentWeather

    init {
        fetchRealtimeWeather()
    }

    private fun fetchRealtimeWeather() = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke().distinctUntilChanged().collect {currentWeather ->
            _currentWeather.postValue(currentWeather)
        }
    }
}