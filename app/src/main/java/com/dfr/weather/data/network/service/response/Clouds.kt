package com.dfr.weather.data.network.service.response

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int,
)