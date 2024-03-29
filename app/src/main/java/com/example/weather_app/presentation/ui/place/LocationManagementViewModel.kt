package com.example.weather_app.presentation.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.core.Response
import com.example.weather_app.domain.usecase.place.DeleteAllPlacesUseCase
import com.example.weather_app.domain.usecase.place.DeletePlaceUseCase
import com.example.weather_app.domain.usecase.place.FetchAllPlacesUseCase
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
                is Response.Loading ->  _places.postValue(UiState.Loading())

                is Response.Success -> if (state.data != null) {
                    _places.postValue(UiState.Loading())
                    _places.postValue(
                        UiState.Success(state.data.map { placeUiMapper.mapToUi(it) })
                    )
                }

                is Response.Error -> {}
            }
        }
    }

    fun deletePlacesExceptCurrent(currentPlace: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteAllPlacesUseCase.invoke(currentPlace)
    }

    fun deletePlace(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        deletePlaceUseCase.invoke(id)
    }
}