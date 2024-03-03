package com.example.weather_app.remote.koin

import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.core.Config
import com.example.weather_app.data.source.PlaceRemoteDataSource
import com.example.weather_app.remote.api.PlaceService
import com.example.weather_app.remote.mapper.place.AutocompletePlaceResponseMapper
import com.example.weather_app.remote.mapper.place.PlaceResponseMapper
import com.example.weather_app.remote.mapper.weather.CityResponseMapper
import com.example.weather_app.remote.mapper.weather.CloudsResponseMapper
import com.example.weather_app.remote.mapper.weather.CoordinatesResponseMapper
import com.example.weather_app.remote.mapper.weather.CurrentWeatherResponseMapper
import com.example.weather_app.remote.mapper.weather.ForecastItemResponseMapper
import com.example.weather_app.remote.mapper.weather.ForecastWeatherResponseMapper
import com.example.weather_app.remote.mapper.weather.MainInfoResponseMapper
import com.example.weather_app.remote.mapper.weather.SysResponseMapper
import com.example.weather_app.remote.mapper.weather.WeatherResponseMapper
import com.example.weather_app.remote.mapper.weather.WindResponseMapper
import com.example.weather_app.remote.source.PlaceRemoteDataSourceImpl
import com.example.weather_app.remote.source.WeatherRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherApiModule = module {
    single(named("WEATHER_API")) { provideWeatherRetrofit(get()) }
    single { provideWeatherService(get(named("WEATHER_API"))) }

    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get(), get(), get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }

    factory { CoordinatesResponseMapper() }
    factory { CurrentWeatherResponseMapper(get(), get(), get(), get(), get(), get()) }
    factory { MainInfoResponseMapper() }
    factory { SysResponseMapper() }
    factory { WeatherResponseMapper() }
    factory { WindResponseMapper() }
    factory { CloudsResponseMapper() }

    factory { CityResponseMapper(get()) }
    factory { ForecastItemResponseMapper(get(), get(), get(), get()) }
    factory { ForecastWeatherResponseMapper(get(), get()) }
}

val placesApiModule = module {
    single(named("PLACE_API")) { providePlaceRetrofit(get()) }
    single { providePlaceService(get(named("PLACE_API"))) }
    single<PlaceRemoteDataSource> { PlaceRemoteDataSourceImpl(get(), get(), get()) }
    factory { AutocompletePlaceResponseMapper(get()) }
    factory { PlaceResponseMapper() }
}

private fun providePlaceRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(Config.BASE_URL_PLACE_API)
        .build()
}

private fun providePlaceService(retrofit: Retrofit): PlaceService {
    return retrofit.create(PlaceService::class.java)
}

private fun provideWeatherRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(Config.BASE_URL_WEATHER_API)
        .build()
}

private fun provideWeatherService(retrofit: Retrofit): WeatherService {
    return retrofit.create(WeatherService::class.java)
}

private fun provideInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY
    }
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}