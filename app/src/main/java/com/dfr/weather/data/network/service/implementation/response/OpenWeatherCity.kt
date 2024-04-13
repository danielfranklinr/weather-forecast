package com.dfr.weather.data.network.service.implementation.response

import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherCity(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String? = null,
)