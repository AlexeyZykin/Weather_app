package com.example.weather_app.presentation.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.FetchForecastByDayUseCase
import com.example.weather_app.domain.usecase.FetchForecastByTimeUseCase
import com.example.weather_app.presentation.mapper.ForecastItemUiMapper
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ForecastDetailsViewModel(
    private val fetchForecastByTimeUseCase: FetchForecastByTimeUseCase,
    private val fetchForecastByDayUseCase: FetchForecastByDayUseCase,
    private val forecastItemUiMapper: ForecastItemUiMapper
) : ViewModel() {
    private val _hourlyForecastDetails = MutableLiveData<UiState<ForecastItemUi>>()
    val hourlyForecastDetails: LiveData<UiState<ForecastItemUi>> get() = _hourlyForecastDetails
    private val _dailyForecastDetails = MutableLiveData<UiState<List<ForecastItemUi>>>()
    val dailyForecastDetails: LiveData<UiState<List<ForecastItemUi>>> get() = _dailyForecastDetails


    fun fetchHourlyForecast(dt: Long) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastByTimeUseCase.invoke(dt).distinctUntilChanged().collect { state ->
            when (state)  {
                is Response.Loading -> _hourlyForecastDetails.postValue(
                    UiState.Loading()
                )

                is Response.Success -> if (state.data != null) {
                    _hourlyForecastDetails.postValue(UiState.Loading())
                    _hourlyForecastDetails.postValue(
                        UiState.Success(forecastItemUiMapper.mapToUi(state.data))
                    )
                }

                is Response.Error -> _hourlyForecastDetails.postValue(UiState.Error(state.msg))
            }
        }
    }

    fun fetchDailyForecast(dtTxt: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastByDayUseCase.invoke(dtTxt).distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> _dailyForecastDetails.postValue(
                    UiState.Loading()
                )

                is Response.Success -> if (state.data != null) {
                    _dailyForecastDetails.postValue(UiState.Loading())
                    dailyForecastHandling(state.data.map { forecastItemUiMapper.mapToUi(it) })
                }

                is Response.Error -> _dailyForecastDetails.postValue(UiState.Error(state.msg))
            }
        }
    }

    private fun dailyForecastHandling(data: List<ForecastItemUi>) {
        val day = data.findLast { it.partOfDay == "d" } ?: data.find { it.partOfDay == "n" }
        val night = data.findLast { it.partOfDay == "n" } ?: data.find { it.partOfDay == "d" }
        if (day != null && night != null) {
            val list = listOf(day, night)
            _dailyForecastDetails.postValue(UiState.Success(list))
        }
    }
}