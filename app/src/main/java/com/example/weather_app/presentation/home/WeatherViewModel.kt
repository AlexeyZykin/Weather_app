package com.example.weather_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.domain.usecase.FetchRealtimeWeatherUseCase
import com.example.weather_app.remote.model.RealtimeWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchRealtimeWeatherUseCase: FetchRealtimeWeatherUseCase
) : ViewModel() {
    private val _realtimeWeather = MutableLiveData<RealtimeWeatherResponse>()
    val realtimeWeather: LiveData<RealtimeWeatherResponse> get() = _realtimeWeather

    init {
        fetchRealtimeWeather()
    }

    private fun fetchRealtimeWeather() = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke().distinctUntilChanged().collect {realtimeWeather ->
            _realtimeWeather.postValue(realtimeWeather)
        }
    }
}