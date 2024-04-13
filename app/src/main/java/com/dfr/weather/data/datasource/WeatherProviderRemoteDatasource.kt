package com.dfr.weather.data.datasource

import com.dfr.weather.data.model.WeatherCityInformation
import com.dfr.weather.data.model.WeatherData
import io.reactivex.rxjava3.core.Single

interface WeatherProviderRemoteDatasource {

    fun getWeatherByCoordinates(
        latitude: String,
        longitude: String,
        apiKey: String,
    ): Single<WeatherData>

    fun getCityInformation(
        cityName: String,
        limit: Int = 1,
        apiKey: String,
    ): Single<WeatherCityInformation>

}