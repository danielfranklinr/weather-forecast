package com.dfr.weather.data.network.service.implementation.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    val degree: Int,
    val speed: Double,
)