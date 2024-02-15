package com.example.weather_app.remote.koin

import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.core.Constants
import com.example.weather_app.remote.mapper.CloudsResponseMapper
import com.example.weather_app.remote.mapper.CoordinatesResponseMapper
import com.example.weather_app.remote.mapper.CurrentWeatherResponseMapper
import com.example.weather_app.remote.mapper.MainInfoResponseMapper
import com.example.weather_app.remote.mapper.SysResponseMapper
import com.example.weather_app.remote.mapper.WeatherResponseMapper
import com.example.weather_app.remote.mapper.WindResponseMapper
import com.example.weather_app.remote.source.WeatherRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { provideRetrofit(get()) }
    single { provideWeatherService(get()) }
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get(), get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }

    factory { CoordinatesResponseMapper() }
    factory { CurrentWeatherResponseMapper(get(), get(), get(), get(), get(), get()) }
    factory { MainInfoResponseMapper() }
    factory { SysResponseMapper() }
    factory { WeatherResponseMapper() }
    factory { WindResponseMapper() }
    factory { CloudsResponseMapper() }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(Constants.BASE_URL_WEATHER_API)
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