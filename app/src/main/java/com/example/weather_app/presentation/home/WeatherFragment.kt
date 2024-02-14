package com.example.weather_app.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherBinding
import com.example.weather_app.presentation.utils.DateTypeConverter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

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

    @SuppressLint("SetTextI18n")
    private fun subscribeObserver() {
        viewModel.currentWeather.observe(viewLifecycleOwner) {
            it?.let { currentWeather ->
                binding.tvTemp.text =
                    "${currentWeather.main.temp}${getString(R.string.metric_celsius)}"
                binding.tvRealtimeWeatherTitle.text = currentWeather.weather.first().main
                binding.tvRealtimeWeatherDesc.text =
                    currentWeather.weather.first().description.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    }
                binding.tvUpdateTime.text =
                    DateTypeConverter.convertUnixToDateString(currentWeather.dt)
                binding.tvFeelsLike.text =
                    "${currentWeather.main.feelsLike}${getString(R.string.metric_celsius)}"
                binding.tvWindSpeed.text =
                    "${currentWeather.wind.speed} ${getString(R.string.metric_wind_speed)}"
                binding.tvHumidity.text =
                    "${currentWeather.main.humidity} ${getString(R.string.metric_percent)}"
                binding.tvPressure.text =
                   "${currentWeather.main.pressure} ${getString(R.string.metric_pressure)}"
            }
        }
    }


}