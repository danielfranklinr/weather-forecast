package com.dfr.weather.domain.model

data class SearchableCityDTO(
    val isoId: String,
    val name: String,
    val latitude: String? = null,
    val longitude: String? = null,
)
