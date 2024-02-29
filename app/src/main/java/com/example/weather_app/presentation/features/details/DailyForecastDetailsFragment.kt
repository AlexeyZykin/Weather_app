package com.example.weather_app.presentation.features.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentDailyForecastDetailsBinding
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.UiState
import com.example.weather_app.presentation.utils.iconRes
import org.koin.androidx.viewmodel.ext.android.viewModel


class DailyForecastDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDailyForecastDetailsBinding
    private val args by navArgs<DailyForecastDetailsFragmentArgs>()
    private val viewModel by viewModel<ForecastDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyForecastDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(findNavController())
        viewModel.fetchDailyForecast(args.dtTxt)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.dailyForecastDetails.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> state.data?.let { updateView(it) }

                is UiState.Error -> Toast.makeText(
                    requireActivity(),
                    state.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(data: List<ForecastItemUi>) {
        binding.tvDailyForecastDetailsDate.text =
            DateTypeConverter.convertUnixToDailyForecastDetailsDate(data.first().dt)
        val dayForecast = data.first()
        val nightForecast = data.last()
        val celsiusString = getString(R.string.metric_celsius)

        binding.tvTempDay.text = "${dayForecast.mainInfo.temp}${celsiusString}"
        binding.tvDayForecastFeelsLike.text = "${getString(R.string.feels_like)} " +
            "${dayForecast.mainInfo.feelsLike}${getString(R.string.metric_celsius)}"
        binding.tvDayForecastWind.text = "${getString(R.string.wind)} " +
            "${dayForecast.wind.speed} ${getString(R.string.metric_wind_speed)}"
        binding.tvDayForecastPrecipitation.text = getString(R.string.probability_of_precipitation) +
                " ${dayForecast.probabilityOfPrecipitation}${getString(R.string.metric_percent)}"
        val iconDayRes = dayForecast.weatherType.iconRes("d")
        Glide.with(requireContext()).load(iconDayRes).into(binding.icDayForecast)

        binding.tvTempNight.text = "${nightForecast.mainInfo.temp}${celsiusString}"
        binding.tvNightForecastFeelsLike.text = "${getString(R.string.feels_like)} " +
                "${nightForecast.mainInfo.feelsLike}${getString(R.string.metric_celsius)}"
        binding.tvNightForecastWind.text = "${getString(R.string.wind)} " +
                "${nightForecast.wind.speed} ${getString(R.string.metric_wind_speed)}"
        binding.tvNightForecastPrecipitation.text = getString(R.string.probability_of_precipitation) +
                " ${nightForecast.probabilityOfPrecipitation}${getString(R.string.metric_percent)}"
        val iconNightRes = nightForecast.weatherType.iconRes("n")
        Glide.with(requireContext()).load(iconNightRes).into(binding.icNightForecast)
    }
}