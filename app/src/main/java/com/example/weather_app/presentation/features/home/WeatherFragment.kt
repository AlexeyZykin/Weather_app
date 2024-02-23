package com.example.weather_app.presentation.features.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherBinding
import com.example.weather_app.presentation.dialog.PermissionDialog
import com.example.weather_app.presentation.model.CurrentWeatherUi
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.iconRes
import com.example.weather_app.presentation.utils.imageRes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class WeatherFragment : Fragment(), HourlyForecastAdapter.ClickListener, DailyForecastAdapter.ClickListener {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel by viewModel<WeatherViewModel>()
    private var isToolbarExpanded = true
    private val hourlyForecastAdapter: HourlyForecastAdapter by lazy {
        HourlyForecastAdapter(this@WeatherFragment)
    }
    private val dailyForecastAdapter: DailyForecastAdapter by lazy {
        DailyForecastAdapter(this@WeatherFragment)
    }
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
        binding.toolbar.setupWithNavController(findNavController())
        binding.appBarLayout.setExpanded(isToolbarExpanded)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        initRecyclerView()
        permissionListener()
        subscribeObservers()
        getWeatherByCurrentLocation()
        savedInstanceState?.let {
            isToolbarExpanded = it.getBoolean(KEY_TOOLBAR_EXPANDED, true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            getWeatherByCurrentLocation()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            val isExpanded = verticalOffset == 0
            binding.swipeRefresh.isEnabled = isExpanded
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_TOOLBAR_EXPANDED, isToolbarExpanded)
    }

   private fun initRecyclerView() {
       binding.rvDailyForecast.layoutManager = LinearLayoutManager(requireContext())
       binding.rvDailyForecast.adapter = dailyForecastAdapter
       binding.rvHourlyForecast.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       binding.rvHourlyForecast.adapter = hourlyForecastAdapter
    }

    private fun subscribeObservers() {
        viewModel.currentWeather.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CurrentWeatherUiState.Loading -> alertDialog.show()

                is CurrentWeatherUiState.Success -> {
                    alertDialog.cancel()
                    updateView(state.data)
                }

                is CurrentWeatherUiState.Error -> {
                    alertDialog.cancel()
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.hourlyForecast.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ForecastUiState.Loading -> {}//alertDialog.show()

                is ForecastUiState.Success -> {
                    //alertDialog.cancel()
                    hourlyForecastAdapter.map(state.data)
                }

                is ForecastUiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.dailyForecast.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ForecastUiState.Loading -> {}

                is ForecastUiState.Success -> dailyForecastAdapter.map(state.data)

                is ForecastUiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(currentWeather: CurrentWeatherUi) {
        weatherImageListener(currentWeather)
        getCityName(currentWeather.coord.lat, currentWeather.coord.lon)
        binding.imgSwipeDown.visibility = View.GONE
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
        return dt !in (sunrise..<sunset)
    }

    private fun weatherImageListener(weather: CurrentWeatherUi) {
        val isNight = isNight(weather.dt, weather.sys.sunrise, weather.sys.sunset)
        val imageRes = weather.weatherType.imageRes(isNight)
        Glide.with(requireContext()).load(imageRes).into(binding.imageWeather)

        val partOfDay = if (isNight) "n" else "d"
        val iconRes = weather.weatherType.iconRes(partOfDay)
        Glide.with(requireContext()).load(iconRes).into(binding.iconWeather)
    }


    private fun getCityName(lat: Double, long: Double) {
        val cityName: String?
        //todo("change geocoder language")
        val geoCoder = Geocoder(requireContext(), Locale("en"))
        val address = geoCoder.getFromLocation(lat, long, 3)
        cityName = address?.get(0)?.locality
        cityName?.let { binding.collapsingToolbar.title = cityName }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (!it) requestForegroundLocationDialog()
            binding.imgSwipeDown.visibility = View.VISIBLE
        }
    }

    private fun requestForegroundLocationDialog() {
        PermissionDialog.foregroundLocationDialog(
            requireContext(),
            object : PermissionDialog.Listener {
                override fun onCLick() {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", requireContext().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            })
    }

    private fun requestPermission() {
        if (!checkLocationPermission())
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    private fun getWeatherByCurrentLocation() {
        if (checkLocationPermission()) {
            if (isLocationEnabled()) getLocation()
            else requestLocationSettingsDialog()
        } else requestPermission()
    }

    private fun isLocationEnabled(): Boolean {
        val lm = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun requestLocationSettingsDialog() {
        PermissionDialog.locationSettingsDialog(
            requireContext(),
            object : PermissionDialog.Listener {
                override fun onCLick() {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
    }

    private fun getLocation() {
        if (!checkLocationPermission()) return
        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            val location = task.result
            if (location != null) {
                viewModel.fetchRealtimeWeather(task.result.latitude, task.result.longitude)
                viewModel.fetchForecast(task.result.latitude, task.result.longitude)
            }
        }
    }

    override fun onClickDailyForecast(forecastItem: ForecastItemUi) {
        TODO("Not yet implemented")
    }

    override fun onClickHourlyForecast(forecastItem: ForecastItemUi) {
        val action = WeatherFragmentDirections.actionWeatherFragmentToHourlyForecastDetailsFragment(forecastItem.dt)
        findNavController().navigate(action)
        isToolbarExpanded = false
    }


    companion object {
        const val KEY_TOOLBAR_EXPANDED = "key_toolbar_expanded"
    }

}