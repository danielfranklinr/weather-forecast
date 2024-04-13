package com.dfr.weather.data.network.service.implementation.response

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int,
)