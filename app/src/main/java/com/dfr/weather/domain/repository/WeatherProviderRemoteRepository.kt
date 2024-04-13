package com.dfr.weather.domain.repository

import com.dfr.weather.domain.model.WeatherCityDTO
import com.dfr.weather.domain.model.WeatherDataDTO
import io.reactivex.rxjava3.core.Single

interface WeatherProviderRemoteRepository {
    fun getWeatherByCoordinates(
        latitude: String,
        longitude: String,
        apikey: String,
    ): Single<WeatherDataDTO>

    fun getCityCoordinates(cityName: String, apiKey: String): Single<WeatherCityDTO>

}