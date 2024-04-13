package com.dfr.weather.domain.usecase

import com.dfr.weather.domain.model.WeatherDataDTO
import io.reactivex.rxjava3.core.Single

interface GetCurrentWeatherByCoordinatesUseCase {
    fun getCurrentWeatherByCoordinates(
        latitude: String,
        longitude: String,
    ): Single<WeatherDataDTO>
}