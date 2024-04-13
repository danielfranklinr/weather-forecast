package com.dfr.weather.data.network.service.implementation.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("1h")
    val lastHourVolume: Double? = null,
    @SerialName("3h")
    val lastThreeHourVolume: Double? = null,

    )