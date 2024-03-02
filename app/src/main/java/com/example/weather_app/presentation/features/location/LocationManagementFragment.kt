package com.example.weather_app.presentation.features.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_app.databinding.FragmentLocationManagementBinding
import com.example.weather_app.presentation.dialog.DialogManager
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationManagementFragment : Fragment() {
    private lateinit var binding: FragmentLocationManagementBinding
    private lateinit var placeAdapter: PlaceAdapter
    private val viewModel by viewModel<LocationManagementViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(findNavController())
        initRecyclerView()
        subscribeObserver()
        viewModel.fetchAllPlaces()
        binding.toolbar.setOnMenuItemClickListener { deleteAllPlaces() }
        binding.fabAddLocation.setOnClickListener {
            val action = LocationManagementFragmentDirections.actionLocationManagementFragmentToSearchPlaceFragment()
            findNavController().navigate(action)
        }
    }

    private fun subscribeObserver() {
        viewModel.places.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> if (state.data != null) placeAdapter.map(state.data)

                is UiState.Error -> {}
            }
        }
    }

    private fun initRecyclerView() {
        placeAdapter = PlaceAdapter(object : PlaceAdapter.ClickListener{
            override fun onClick(place: PlaceUi) {
                TODO("Not yet implemented")
            }

            override fun onLongClick(place: PlaceUi) {
                deletePlace(place)
            }
        })
        binding.rvPlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlaces.adapter = placeAdapter
    }

    private fun deleteAllPlaces(): Boolean {
        DialogManager.deleteAllPlaces(requireContext(), object : DialogManager.Listener {
            override fun onClick() {
                viewModel.deleteAllPlaces()
            }

        })
        return true
    }

    private fun deletePlace(place: PlaceUi) {
        DialogManager.deletePlace(requireContext(), object : DialogManager.Listener{
            override fun onClick() {
                place.id?.let { viewModel.deletePlace(it) }
            }
        })
    }
}