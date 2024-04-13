package com.dfr.weather.domain.usecase

import android.content.Context
import com.dfr.weather.R
import com.dfr.weather.domain.model.WeatherDataDTO
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrentWeatherByCityNameUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
) : GetCurrentWeatherByCityNameUseCase {


    override fun getCurrentWeatherByCityName(
        cityName: String,
    ): Single<WeatherDataDTO> {
        val apiKey = context.getString(R.string.service_open_weather_api_key)

        return weatherProviderRemoteRepository.getCityCoordinates(cityName, apiKey).flatMap {
            weatherProviderRemoteRepository.getWeatherByCoordinates(
                it.latitude.toString(),
                it.longitude.toString(),
                apiKey
            )
        }
    }

}