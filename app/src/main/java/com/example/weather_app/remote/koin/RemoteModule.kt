package com.example.weather_app.remote.koin

import com.example.weather_app.data.source.WeatherRemoteDataSource
import com.example.weather_app.remote.api.WeatherService
import com.example.weather_app.remote.constants.ApiConstants
import com.example.weather_app.remote.source.WeatherRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { provideRetrofit(get()) }
    single { provideWeatherService(get()) }
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }

}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(ApiConstants.BASE_URL_WEATHER_API)
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