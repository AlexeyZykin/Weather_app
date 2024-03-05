package com.example.weather_app.presentation.features.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.core.Config
import com.example.weather_app.databinding.FragmentWeatherBinding
import com.example.weather_app.presentation.dialog.PermissionDialog
import com.example.weather_app.presentation.utils.UiState
import com.example.weather_app.presentation.model.CurrentWeatherUi
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.iconRes
import com.example.weather_app.presentation.utils.imageRes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class WeatherFragment : Fragment(), HourlyForecastAdapter.ClickListener,
    DailyForecastAdapter.ClickListener {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var pLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel by viewModel<WeatherViewModel>()
    private var isToolbarExpanded = true
    private var isPermissionDialogShown = false
    private val hourlyForecastAdapter: HourlyForecastAdapter by lazy {
        HourlyForecastAdapter(this@WeatherFragment)
    }
    private val dailyForecastAdapter: DailyForecastAdapter by lazy {
        DailyForecastAdapter(this@WeatherFragment)
    }
    private val sharedPref: SharedPreferences by lazy {
        requireContext().getSharedPreferences(Config.APP_PREFS, Context.MODE_PRIVATE)
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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.toolbar.setupWithNavController(findNavController())
        binding.appBarLayout.setExpanded(isToolbarExpanded)
        loadSelectedPlace()
        initRecyclerView()
        setupMenu()
        permissionListener()
        subscribeObservers()
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

    override fun onResume() {
        super.onResume()
        getWeatherByCurrentLocation()
    }


    private fun setupMenu() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_item -> {
                    val action =
                        WeatherFragmentDirections.actionWeatherFragmentToLocationManagementFragment()
                    findNavController().navigate(action)
                    isToolbarExpanded = true
                    true
                }

                R.id.refreshMenuItem -> {
                    getWeatherByCurrentLocation()
                    true
                }

                else -> false
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_TOOLBAR_EXPANDED, isToolbarExpanded)
    }

    private fun initRecyclerView() {
        binding.rvDailyForecast.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDailyForecast.adapter = dailyForecastAdapter
        binding.rvHourlyForecast.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHourlyForecast.adapter = hourlyForecastAdapter
    }

    private fun subscribeObservers() {
        viewModel.currentWeather.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> binding.swipeRefresh.isRefreshing = true

                is UiState.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    state.data?.let { updateView(it) }
                }

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.hourlyForecast.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> state.data?.let { hourlyForecastAdapter.map(it) }

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.dailyForecast.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}

                is UiState.Success -> state.data?.let { dailyForecastAdapter.map(it) }

                is UiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(currentWeather: CurrentWeatherUi) {
        weatherImageListener(currentWeather)
        setupTitle()
        setupSelectedPlace()
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

    private fun setupTitle() {
        val selectedPlace = sharedPref.getString(Config.SHARED_PREFS_SELECTED_PLACE, "")
        val currentPlace = sharedPref.getString(Config.SHARED_PREFS_CURRENT_PLACE, "")
        binding.collapsingToolbar.title = if (selectedPlace == "") currentPlace else selectedPlace
    }

    private fun setupCurrentPlace(lat: Double, lon: Double) {
        val geoCoder = Geocoder(requireContext(), Locale("en"))
        val address = geoCoder.getFromLocation(lat, lon, 3)
        val cityName: String? = address?.get(0)?.locality
        cityName?.let { city ->
            sharedPref.edit().putString(Config.SHARED_PREFS_CURRENT_PLACE, city).apply()
        }
    }

    private fun setupSelectedPlace() {
        val selectedPlace = sharedPref.getString(Config.SHARED_PREFS_SELECTED_PLACE, "")
        if (selectedPlace == "") {
            val currentPlace = sharedPref.getString(Config.SHARED_PREFS_CURRENT_PLACE, "")
            sharedPref.edit().putString(Config.SHARED_PREFS_SELECTED_PLACE, currentPlace).apply()
            currentPlace?.let { viewModel.fetchPlace(it) }
        }
    }

    private fun loadSelectedPlace() {
        val selectedPlace = sharedPref.getString(Config.SHARED_PREFS_SELECTED_PLACE, "")
        if (selectedPlace != "")
            selectedPlace?.let { viewModel.loadPlace(it) }

    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allPermissionsGranted = permissions.all { it.value }

            if (!allPermissionsGranted && !isPermissionDialogShown) {
                isPermissionDialogShown = true
                requestForegroundLocationDialog()
            }
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
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (!checkLocationPermission())
            pLauncher.launch(permissions)
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
                setupCurrentPlace(task.result.latitude, task.result.longitude)
                showWeather(task.result.latitude, task.result.longitude)
            }
        }
    }

    override fun onClickDailyForecast(forecastItem: ForecastItemUi) {
        val dtTxt = DateTypeConverter.convertUnixToDayForecastDateRequest(forecastItem.dt)
        val action = WeatherFragmentDirections.actionWeatherFragmentToDailyForecastDetailsFragment(
            dtTxt
        )
        findNavController().navigate(action)
        isToolbarExpanded = false
    }

    override fun onClickHourlyForecast(forecastItem: ForecastItemUi) {
        val action = WeatherFragmentDirections.actionWeatherFragmentToHourlyForecastDetailsFragment(
            forecastItem.dt
        )
        findNavController().navigate(action)
        isToolbarExpanded = false
    }

    private fun showWeather(lat: Double, lon: Double) {
        val selectedPlace = sharedPref.getString(Config.SHARED_PREFS_SELECTED_PLACE, "")
        if (selectedPlace == "") {
            viewModel.fetchRealtimeWeather(lat, lon)
            viewModel.fetchForecast(lat, lon)
        } else {
            viewModel.place.observe(viewLifecycleOwner) { place ->
                viewModel.fetchRealtimeWeather(place.lat, place.lon)
                viewModel.fetchForecast(place.lat, place.lon)
            }
        }
    }

    companion object {
        const val KEY_TOOLBAR_EXPANDED = "key_toolbar_expanded"
    }
}