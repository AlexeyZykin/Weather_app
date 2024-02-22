package com.example.weather_app.presentation.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentHourlyForecastDetailsBinding

class HourlyForecastDetailsFragment : Fragment() {
    private lateinit var binding: FragmentHourlyForecastDetailsBinding
    private val args by navArgs<HourlyForecastDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHourlyForecastDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(findNavController())
    }



}