package com.dfr.weather.domain.usecase.implementation

import android.content.Context
import com.dfr.weather.R
import com.dfr.weather.domain.model.WeatherDataDTO
import com.dfr.weather.domain.repository.DeviceLocationRepository
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
import com.dfr.weather.domain.usecase.GetMyLocationCurrentWeatherUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMyLocationCurrentWeatherUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val deviceLocationRepository: DeviceLocationRepository,
    private val getWeatherProviderRemoteRepository: WeatherProviderRemoteRepository,
) : GetMyLocationCurrentWeatherUseCase {


    override fun getMyLocationCurrentWeather(): Single<WeatherDataDTO> {


        return deviceLocationRepository.getDeviceLocation().flatMap {
            val apiKey = context.getString(R.string.service_open_weather_api_key)

            getWeatherProviderRemoteRepository.getWeatherByCoordinates(
                it.latitude.toString(),
                it.longitude.toString(),
                apiKey
            )
        }
    }
}