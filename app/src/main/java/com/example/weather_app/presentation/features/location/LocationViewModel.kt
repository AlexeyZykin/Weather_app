package com.example.weather_app.presentation.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.FetchAutocompletePlacesUseCase
import com.example.weather_app.presentation.mapper.place.AutocompletePlaceUiMapper
import com.example.weather_app.presentation.model.place.AutocompletePlaceUi
import com.example.weather_app.presentation.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class LocationViewModel(
    private val fetchAutocompletePlacesUseCase: FetchAutocompletePlacesUseCase,
    private val autocompletePlaceUiMapper: AutocompletePlaceUiMapper
) : ViewModel() {
    private val _autocompletePlaces = MutableLiveData<UiState<AutocompletePlaceUi>>()
    val autocompletePlaces: LiveData<UiState<AutocompletePlaceUi>> get() = _autocompletePlaces


    fun fetchAutocompletePlaces(inputText: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchAutocompletePlacesUseCase.invoke(inputText).distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> if (state.isLoading) _autocompletePlaces.postValue(
                    UiState.Loading(true)
                )

                is Response.Success -> if (state.data != null) {
                    _autocompletePlaces.postValue(UiState.Loading(false))
                    _autocompletePlaces.postValue(
                        UiState.Success(autocompletePlaceUiMapper.mapToUi(state.data))
                    )
                }

                is Response.Error -> _autocompletePlaces.postValue(UiState.Error(state.msg))
            }
        }
    }
}