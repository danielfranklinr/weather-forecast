package com.dfr.weather.domain.usecase

import com.dfr.weather.domain.model.WeatherCityDTO
import io.reactivex.rxjava3.core.Single

interface GetCityCoordinatesUseCase {
    fun getCityCoordinates(
        cityName: String,
    ): Single<WeatherCityDTO>
}