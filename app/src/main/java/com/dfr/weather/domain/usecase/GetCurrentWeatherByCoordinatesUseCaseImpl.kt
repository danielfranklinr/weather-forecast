package com.dfr.weather.domain.usecase

import android.content.Context
import com.dfr.weather.R
import com.dfr.weather.domain.model.WeatherDataDTO
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrentWeatherByCoordinatesUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
) : GetCurrentWeatherByCoordinatesUseCase {

    override fun getCurrentWeatherByCoordinates(
        latitude: String,
        longitude: String,
    ): Single<WeatherDataDTO> {
        val apiKey = context.getString(R.string.service_open_weather_api_key)

        return weatherProviderRemoteRepository.getWeatherByCoordinates(latitude, longitude, apiKey)
    }
}