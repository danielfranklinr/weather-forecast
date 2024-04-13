package com.dfr.weather.domain.repository

import android.content.Context
import com.dfr.weather.data.datasource.WeatherProviderRemoteDatasource
import com.dfr.weather.domain.model.WeatherCityDTO
import com.dfr.weather.domain.model.WeatherDataDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherProviderRemoteRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherRemoteDatasource: WeatherProviderRemoteDatasource,
) : WeatherProviderRemoteRepository {

    override fun getWeatherByCoordinates(
        latitude: String,
        longitude: String,
        apikey: String,
    ): Single<WeatherDataDTO> {
        return weatherRemoteDatasource.getWeatherByCoordinates(latitude, longitude, apikey)
            .map { weatherData -> WeatherDataDTO.fromWeatherData(context, weatherData) }
    }

    override fun getCityCoordinates(cityName: String, apiKey: String): Single<WeatherCityDTO> {
        return weatherRemoteDatasource.getCityInformation(cityName = cityName, apiKey = apiKey)
            .map { city -> WeatherCityDTO.fromCity(city) }
    }

}