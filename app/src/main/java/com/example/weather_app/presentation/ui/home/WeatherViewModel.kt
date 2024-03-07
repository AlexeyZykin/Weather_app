package com.example.weather_app.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.weather.FetchForecastUseCase
import com.example.weather_app.domain.usecase.place.FetchPlaceUseCase
import com.example.weather_app.domain.usecase.weather.FetchRealtimeWeatherUseCase
import com.example.weather_app.domain.usecase.place.LoadPlaceUseCase
import com.example.weather_app.presentation.utils.UiState
import com.example.weather_app.presentation.mapper.weather.CurrentWeatherUiMapper
import com.example.weather_app.presentation.mapper.weather.ForecastWeatherUiMapper
import com.example.weather_app.presentation.mapper.place.PlaceUiMapper
import com.example.weather_app.presentation.model.weather.CurrentWeatherUi
import com.example.weather_app.presentation.model.weather.ForecastItemUi
import com.example.weather_app.presentation.model.place.PlaceUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchRealtimeWeatherUseCase: FetchRealtimeWeatherUseCase,
    private val fetchForecastUseCase: FetchForecastUseCase,
    private val fetchPlaceUseCase: FetchPlaceUseCase,
    private val loadPlaceUseCase: LoadPlaceUseCase,
    private val currentWeatherUiMapper: CurrentWeatherUiMapper,
    private val forecastWeatherUiMapper: ForecastWeatherUiMapper,
    private val placeUiMapper: PlaceUiMapper
) : ViewModel() {

    private val _currentWeather = MutableLiveData<UiState<CurrentWeatherUi>>()
    val currentWeather: LiveData<UiState<CurrentWeatherUi>> get() = _currentWeather
    private val _hourlyForecast = MutableLiveData<UiState<List<ForecastItemUi>>>()
    val hourlyForecast: LiveData<UiState<List<ForecastItemUi>>> get() = _hourlyForecast
    private val _dailyForecast = MutableLiveData<UiState<List<ForecastItemUi>>>()
    val dailyForecast: LiveData<UiState<List<ForecastItemUi>>> get() = _dailyForecast
    private val _place = MutableLiveData<PlaceUi>()
    val place: LiveData<PlaceUi> get() = _place

    fun fetchRealtimeWeather(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchRealtimeWeatherUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> _currentWeather.postValue(
                    UiState.Loading()
                )

                is Response.Success -> if (it.data != null) {
                _currentWeather.postValue(
                    UiState.Success(currentWeatherUiMapper.mapToUi(it.data))
                )
                }

                is Response.Error -> {
                    _currentWeather.postValue(UiState.Loading())
                    _currentWeather.postValue(UiState.Error(it.msg))
                }
            }
        }
    }

    fun fetchForecast(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        fetchForecastUseCase.invoke(lat, lon).distinctUntilChanged().collect {
            when (it) {
                is Response.Loading -> {
                    _hourlyForecast.postValue(UiState.Loading())
                    _dailyForecast.postValue(UiState.Loading())
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

    fun fetchPlace(city: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchPlaceUseCase.invoke(city).distinctUntilChanged().collect()
    }

    fun loadPlace(city: String) = viewModelScope.launch(Dispatchers.IO) {
        _place.postValue(placeUiMapper.mapToUi(loadPlaceUseCase.invoke(city)))
    }
}