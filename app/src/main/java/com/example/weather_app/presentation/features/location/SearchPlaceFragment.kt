package com.example.weather_app.presentation.features.location

import android.app.Dialog
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentSearchPlaceBinding
import com.example.weather_app.presentation.model.place.AutocompletePlaceUi
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.UiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class SearchPlaceFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchPlaceBinding
    private val viewModel by viewModel<LocationViewModel>()
    private val autocompleteAdapter: AutocompletePlacesAdapter by lazy {
        AutocompletePlacesAdapter()
    }

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
        binding.icCloseDialog.setOnClickListener { findNavController().popBackStack() }
        searchViewListener()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.autocompletePlaces.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> state.data?.let { autocompleteAdapter.map(it.results) }

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvAutocompletePlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAutocompletePlaces.adapter = autocompleteAdapter
    }


    private fun searchViewListener() {
        binding.searchView.onActionViewExpanded()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                addPlace(query)
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

    private fun addPlace(cityName: String?) {
        val geoCoder = Geocoder(requireContext(), Locale("en"))
        val address = geoCoder.getFromLocationName("Moscow", 1)
        if (!address.isNullOrEmpty()) {
            val lat = address[0].latitude
            val lon = address[0].longitude
            // Example: viewModel.addPlace(PlaceUi(cityName, lat, lon))
        }
        else {
            Toast.makeText(requireActivity(), getString(R.string.places_autocomplete_no_results), Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

    }


}