package com.example.weather_app.presentation.features.location

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentSearchPlaceBinding
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.UiState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchPlaceFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchPlaceBinding
    private lateinit var autocompleteAdapter: AutocompletePlacesAdapter
    private val viewModel by viewModel<SearchPlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        searchViewListener()
        subscribeObservers()
        binding.icCloseDialog.setOnClickListener { findNavController().popBackStack() }
    }

    private fun subscribeObservers() {
        viewModel.autocompletePlaces.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> state.data?.let { autocompleteAdapter.map(it.results) }

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.place.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> findNavController().popBackStack()

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        autocompleteAdapter = AutocompletePlacesAdapter(object : AutocompletePlacesAdapter.ClickListener {
            override fun onClick(place: PlaceUi) {
                viewModel.addPlace(place)
                findNavController().popBackStack()
            }
        })
        binding.rvAutocompletePlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAutocompletePlaces.adapter = autocompleteAdapter
    }


    private fun searchViewListener() {
        binding.searchView.onActionViewExpanded()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query.length >= 3)
                    viewModel.fetchPlace(query)
                else
                    Toast.makeText(requireContext(), getString(R.string.places_autocomplete_no_results), Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty() && newText.length >= 3)
                    viewModel.fetchAutocompletePlaces(newText)
                else
                    autocompleteAdapter.map(emptyList())
                return true
            }
        })
    }


    // If the geocoding api stops working, use this geocoder
/*    private fun addPlace(cityName: String) {
        val geoCoder = Geocoder(requireContext(), Locale("en"))
        val address = geoCoder.getFromLocationName(cityName, 1)
        if (!address.isNullOrEmpty()) {
            val lat = address[0].latitude
            val lon = address[0].longitude
            viewModel.addPlace(PlaceUi(city = cityName, lon = lon, lat = lat))
        }
        else {
            Toast.makeText(requireActivity(), getString(R.string.places_autocomplete_no_results), Toast.LENGTH_LONG).show()
        }
    } */

}