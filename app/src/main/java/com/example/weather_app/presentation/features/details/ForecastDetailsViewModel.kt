package com.example.weather_app.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.FetchForecastByTimeUseCase
import com.example.weather_app.presentation.mapper.ForecastItemUiMapper
import com.example.weather_app.presentation.model.ForecastItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ForecastDetailsViewModel(
    private val fetchForecastByTimeUseCase: FetchForecastByTimeUseCase,
    private val forecastItemUiMapper: ForecastItemUiMapper
) : ViewModel() {
    private val _hourlyForecastDetails = MutableLiveData<WeatherUiState<ForecastItemUi>>()
    val hourlyForecastDetails: LiveData<WeatherUiState<ForecastItemUi>> get() = _hourlyForecastDetails

    fun fetchHourlyForecast(dt: Long) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastByTimeUseCase.invoke(dt).distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> if (state.isLoading) _hourlyForecastDetails.postValue(WeatherUiState.Loading(true))

                is Response.Success -> {
                    _hourlyForecastDetails.postValue(WeatherUiState.Loading(isLoading = false))
                    _hourlyForecastDetails.postValue(WeatherUiState.Success(
                        forecastItemUiMapper.mapToUi(state.data)
                    ))
                }

                is Response.Error -> _hourlyForecastDetails.postValue(WeatherUiState.Error(state.msg))
            }
        }
    }
}