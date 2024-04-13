package com.dfr.weather.domain.usecase

import com.dfr.weather.domain.model.WeatherDataDTO
import io.reactivex.rxjava3.core.Single

interface GetMyLocationCurrentWeatherUseCase {
    fun getMyLocationCurrentWeather(
    ): Single<WeatherDataDTO>
}