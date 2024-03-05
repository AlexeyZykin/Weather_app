package com.example.weather_app.presentation.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.AddPlaceUseCase
import com.example.weather_app.domain.usecase.FetchAutocompletePlacesUseCase
import com.example.weather_app.domain.usecase.FetchPlaceUseCase
import com.example.weather_app.presentation.mapper.place.AutocompletePlaceUiMapper
import com.example.weather_app.presentation.mapper.place.PlaceUiMapper
import com.example.weather_app.presentation.model.place.AutocompletePlaceUi
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchPlaceViewModel(
    private val fetchAutocompletePlacesUseCase: FetchAutocompletePlacesUseCase,
    private val fetchPlaceUseCase: FetchPlaceUseCase,
    private val addPlaceUseCase: AddPlaceUseCase,
    private val autocompletePlaceUiMapper: AutocompletePlaceUiMapper,
    private val placeUiMapper: PlaceUiMapper
) : ViewModel() {
    private val _autocompletePlaces = MutableLiveData<UiState<AutocompletePlaceUi>>()
    val autocompletePlaces: LiveData<UiState<AutocompletePlaceUi>> get() = _autocompletePlaces
    private val _place = MutableLiveData<UiState<PlaceUi>>()
    val place: LiveData<UiState<PlaceUi>> get() = _place


    fun fetchAutocompletePlaces(inputText: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchAutocompletePlacesUseCase.invoke(inputText).distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> _autocompletePlaces.postValue(
                    UiState.Loading()
                )

                is Response.Success -> if (state.data != null) {
                    _autocompletePlaces.postValue(UiState.Loading())
                    _autocompletePlaces.postValue(
                        UiState.Success(autocompletePlaceUiMapper.mapToUi(state.data))
                    )
                }

                is Response.Error -> {
                    _autocompletePlaces.postValue(UiState.Loading())
                    _autocompletePlaces.postValue(UiState.Error(state.msg))
                }
            }
        }
    }


    fun fetchPlace(city: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchPlaceUseCase.invoke(city).distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> _place.postValue(UiState.Loading())

                is Response.Success -> {
                    if (state.data != null) {
                        _place.postValue(UiState.Loading())
                        _place.postValue(UiState.Success(placeUiMapper.mapToUi(state.data)))
                    }
                }

                is Response.Error -> {
                    _place.postValue(UiState.Loading())
                    _place.postValue(UiState.Error(state.msg))
                }
            }
        }
    }

    fun addPlace(place: PlaceUi) = viewModelScope.launch(Dispatchers.IO) {
        addPlaceUseCase.invoke(placeUiMapper.mapFromUi(place))
    }


}