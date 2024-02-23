package com.example.weather_app.presentation.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.core.Response
import com.example.weather_app.databinding.FragmentHourlyForecastDetailsBinding
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.iconRes
import com.example.weather_app.presentation.utils.imageRes
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class HourlyForecastDetailsFragment : Fragment() {
    private lateinit var binding: FragmentHourlyForecastDetailsBinding
    private val args by navArgs<HourlyForecastDetailsFragmentArgs>()
    private val viewModel by viewModel<ForecastDetailsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHourlyForecastDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(findNavController())
        viewModel.fetchHourlyForecast(args.dt)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.hourlyForecastDetails.observe(viewLifecycleOwner) { weatherState ->
            when (weatherState) {
                is WeatherUiState.Loading -> if (weatherState.isLoading) binding.progressBar.visibility = View.VISIBLE

                is WeatherUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    weatherState.data?.let { updateView(it) }
                }

                is WeatherUiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireActivity(), weatherState.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateView(data: ForecastItemUi) {
        val isNight = data.partOfDay == "n"
        val imageRes = data.weatherType.imageRes(isNight)
        binding.layout.setBackgroundResource(imageRes)
        binding.toolbar.title = requireContext().getString(R.string.hourly_forecast_toolbar_title)
        binding.tvHourlyForecastDetailsDate.text = DateTypeConverter.convertUnixToHourlyForecastDetailsDate(data.dt)
        val iconRes = data.weatherType.iconRes(data.partOfDay)
        Glide.with(requireContext()).load(iconRes).into(binding.iconWeather)
        binding.tvHourlyForecastDesc.text = data.weather.description.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
        binding.tvFeelsLike.text = "${data.mainInfo.feelsLike}${getString(R.string.metric_celsius)}"
        binding.tvWindSpeed.text = "${data.wind.speed} ${getString(R.string.metric_wind_speed)}"
        binding.tvPrecipitation.text = "${data.probabilityOfPrecipitation}${getString(R.string.metric_percent)}"
    }

}