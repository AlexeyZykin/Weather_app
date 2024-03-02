package com.example.weather_app.presentation.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.DeleteAllPlacesUseCase
import com.example.weather_app.domain.usecase.DeletePlaceUseCase
import com.example.weather_app.domain.usecase.FetchAllPlacesUseCase
import com.example.weather_app.presentation.mapper.place.PlaceUiMapper
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class LocationManagementViewModel(
    private val fetchAllPlacesUseCase: FetchAllPlacesUseCase,
    private val deleteAllPlacesUseCase: DeleteAllPlacesUseCase,
    private val deletePlaceUseCase: DeletePlaceUseCase,
    private val placeUiMapper: PlaceUiMapper
) : ViewModel() {
    private val _places = MutableLiveData<UiState<List<PlaceUi>>>()
    val places: LiveData<UiState<List<PlaceUi>>> get() = _places

    fun fetchAllPlaces() = viewModelScope.launch(Dispatchers.IO) {
        fetchAllPlacesUseCase.invoke().distinctUntilChanged().collect { state ->
            when (state) {
                is Response.Loading -> if (state.isLoading) _places.postValue(UiState.Loading(true))

                is Response.Success -> if (state.data != null) {
                    _places.postValue(UiState.Loading(false))
                    _places.postValue(
                        UiState.Success(state.data.map { placeUiMapper.mapToUi(it) })
                    )
                }

                is Response.Error -> {}
            }
        }
    }

    fun deleteAllPlaces() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllPlacesUseCase.invoke()
    }

    fun deletePlace(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        deletePlaceUseCase.invoke(id)
    }

}