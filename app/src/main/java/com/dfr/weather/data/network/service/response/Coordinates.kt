package com.dfr.weather.data.network.service.response

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val lat: Double,
    val lon: Double,
)