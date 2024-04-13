package com.dfr.weather.domain.usecase

import android.content.Context
import com.dfr.weather.R
import com.dfr.weather.domain.model.WeatherCityDTO
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCityCoordinatesUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
) : GetCityCoordinatesUseCase {

    override fun getCityCoordinates(cityName: String): Single<WeatherCityDTO> {
        val apiKey = context.getString(R.string.service_open_weather_api_key)
        return weatherProviderRemoteRepository.getCityCoordinates(cityName, apiKey)
    }
}