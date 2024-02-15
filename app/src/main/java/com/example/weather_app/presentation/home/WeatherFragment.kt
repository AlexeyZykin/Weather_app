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
import com.squareup.picasso.Picasso
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
                val isNight = isNight(it.dt, it.sys.sunset)
                weatherImageListener(it.weather.first().id, isNight)
                Picasso.get()
                    .load("https://openweathermap.org/img/wn/${it.weather.first().icon}@4x.png")
                    .into(binding.iconWeather)
                binding.tvTemp.text = "${currentWeather.main.temp}${getString(R.string.metric_celsius)}"
                binding.tvRealtimeWeatherTitle.text = currentWeather.weather.first().main
                binding.tvRealtimeWeatherDesc.text =
                    currentWeather.weather.first().description.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    }
                binding.tvUpdateTime.text = DateTypeConverter.convertUnixToDateString(currentWeather.dt)
                binding.tvFeelsLike.text = "${currentWeather.main.feelsLike}${getString(R.string.metric_celsius)}"
                binding.tvWindSpeed.text = "${currentWeather.wind.speed} ${getString(R.string.metric_wind_speed)}"
                binding.tvHumidity.text = "${currentWeather.main.humidity} ${getString(R.string.metric_percent)}"
                binding.tvPressure.text = "${currentWeather.main.pressure} ${getString(R.string.metric_pressure)}"
                binding.tvClouds.text = "${currentWeather.clouds.all} ${getString(R.string.metric_percent)}"
                binding.tvVisibility.text = "${currentWeather.visibility} ${getString(R.string.metric_visibility)}"
            }
        }
    }

    private fun isNight(dt: Long, sunset: Long): Boolean {
        return dt >= sunset
    }

    private fun weatherImageListener(state: Int, isNight: Boolean) {
        val imageUrl = if (isNight) {
            when (state) {
                in 200..202, in 230..232 -> R.drawable.image_thunder_rain
                in 210..221 -> R.drawable.image_night_thunderstorm
                in 300..311 -> R.drawable.image_drizzle
                in 312..321 -> R.drawable.image_night_heavy_drizzle
                in 500..501 -> R.drawable.image_night_light_rain
                in 502..511 -> R.drawable.image_night_rain
                //in 520..531 -> heavy rain
                in 600..601 -> R.drawable.image_night_light_snow
                in 602..611 -> R.drawable.image_night_heavy_snow
                in 612..622 -> R.drawable.image_snow_with_rain
                701, 711, 741 -> R.drawable.image_night_mist
                721 -> R.drawable.image_night_haze
                781 -> R.drawable.image_tornado
                800 -> R.drawable.image_night_clear
                801 -> R.drawable.image_night_few_clouds
                802 -> R.drawable.image_night_clouds
                803, 804 -> R.drawable.image_night_overcast
                else -> R.drawable.image_default
            }
        } else {
            when (state) {
                in 200..202, in 230..232 -> R.drawable.image_thunder_rain
                in 210..221 -> R.drawable.image_day_thunderstorm
                in 300..311 -> R.drawable.image_drizzle
                in 312..321 -> R.drawable.image_day_heavy_drizzle
                in 500..501 -> R.drawable.image_day_light_rain
                in 502..511 -> R.drawable.image_day_rain
                in 520..531 -> R.drawable.image_day_heavy_rain
                in 600..601 -> R.drawable.image_day_snow
                in 602..611 -> R.drawable.image_day_heavy_snow
                in 612..622 -> R.drawable.image_snow_with_rain
                701, 711 -> R.drawable.image_day_mist
                721 -> R.drawable.image_day_haze
                741 -> R.drawable.image_day_fog
                781 -> R.drawable.image_tornado
                800 -> R.drawable.image_day_clear
                801 -> R.drawable.image_day_few_clouds
                802 -> R.drawable.image_day_clouds
                803 -> R.drawable.image_day_broken_clouds
                804 -> R.drawable.image_day_overcast
                else -> R.drawable.image_default
            }
        }
        Picasso.get().load(imageUrl).into(binding.imageWeather)
    }

}