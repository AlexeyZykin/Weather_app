package com.example.weather_app.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherBinding
import com.example.weather_app.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel by viewModel<WeatherViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.realtimeWeather.observe(viewLifecycleOwner) {
            it?.let {
                binding.tvTemp.text = it.main.temp.toInt().toString() + "°c"
                binding.tvRealtimeWeatherTitle.text = it.weather.first().main
            }
        }
    }
}