package com.dfr.weather.data.model

import com.dfr.weather.data.network.service.implementation.response.OpenWeatherCity

data class WeatherCityInformation(
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val state: String? = null,
) {
    companion object {
        fun fromOpenWeatherCity(openWeatherCity: OpenWeatherCity): WeatherCityInformation {

            return WeatherCityInformation(
                openWeatherCity.country,
                openWeatherCity.lat,
                openWeatherCity.lon,
                openWeatherCity.name,
                openWeatherCity.state
            )
        }
    }
}
