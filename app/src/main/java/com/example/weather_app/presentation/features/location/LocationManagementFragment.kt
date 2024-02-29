package com.example.weather_app.presentation.features.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weather_app.databinding.FragmentLocationManagementBinding


class LocationManagementFragment : Fragment() {
    private lateinit var binding: FragmentLocationManagementBinding

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
        binding.fabAddLocation.setOnClickListener {
            val action = LocationManagementFragmentDirections.actionLocationManagementFragmentToSearchPlaceFragment()
            findNavController().navigate(action)
        }
    }
}