package com.example.weather_app.presentation.features.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherBinding
import com.example.weather_app.presentation.model.CurrentWeatherUi
import com.example.weather_app.presentation.model.WeatherType
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.imageRes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.combine
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel by viewModel<WeatherViewModel>()
    private val alertDialog: AlertDialog by lazy {
        AlertDialog.Builder(requireContext()).create()
    }
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
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchRealtimeWeather()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val isExpanded = verticalOffset == 0
            binding.swipeRefresh.isEnabled = isExpanded
        }
    }

    private fun subscribeObserver() {
        viewModel.currentWeather.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CurrentWeatherUiState.Loading -> alertDialog.show()

                is CurrentWeatherUiState.Success -> {
                    alertDialog.cancel()
                    updateView(state.data)
                }

                is CurrentWeatherUiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(currentWeather: CurrentWeatherUi) {
        weatherImageListener(currentWeather)
        Picasso.get()
            .load("https://openweathermap.org/img/wn/${currentWeather.weather.icon}@4x.png")
            .into(binding.iconWeather)
        binding.tvTemp.text = "${currentWeather.main.temp}${getString(R.string.metric_celsius)}"
        binding.tvRealtimeWeatherTitle.text = currentWeather.weather.main
        binding.tvRealtimeWeatherDesc.text =
            currentWeather.weather.description.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
        binding.tvUpdateTime.text = DateTypeConverter.convertUnixToDateString(currentWeather.dt)
        binding.tvFeelsLike.text =
            "${currentWeather.main.feelsLike}${getString(R.string.metric_celsius)}"
        binding.tvWindSpeed.text =
            "${currentWeather.wind.speed} ${getString(R.string.metric_wind_speed)}"
        binding.tvHumidity.text =
            "${currentWeather.main.humidity} ${getString(R.string.metric_percent)}"
        binding.tvPressure.text =
            "${currentWeather.main.pressure} ${getString(R.string.metric_pressure)}"
        binding.tvClouds.text =
            "${currentWeather.clouds.all} ${getString(R.string.metric_percent)}"
        binding.tvVisibility.text =
            "${currentWeather.visibility} ${getString(R.string.metric_visibility)}"
    }

    private fun isNight(dt: Long, sunrise: Long, sunset: Long): Boolean {
        return dt !in (sunrise ..<sunset)
    }

    private fun weatherImageListener(weather: CurrentWeatherUi) {
        val isNight = isNight(weather.dt, weather.sys.sunrise, weather.sys.sunset)
        val imageRes = weather.weatherType.imageRes(isNight)
        Picasso.get().load(imageRes).into(binding.imageWeather)
    }
}