package com.dfr.weather.data.network.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    @SerialName("coord")
    val coordinates: Coordinates,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
)