package com.dfr.weather.domain.model

import com.dfr.weather.data.model.WeatherCityInformation

data class WeatherCityDTO(
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val state: String? = null,
) {
    companion object {
        fun fromCity(weatherCityInformation: WeatherCityInformation): WeatherCityDTO {

            return WeatherCityDTO(
                weatherCityInformation.country,
                weatherCityInformation.latitude,
                weatherCityInformation.longitude,
                weatherCityInformation.name,
                weatherCityInformation.state
            )
        }
    }
}
