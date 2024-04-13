package com.dfr.weather.data.model

import android.content.Context
import com.dfr.weather.data.network.service.response.OpenWeatherResponse
import com.dfr.weather.data.network.transformer.IconUrlTransformer

data class WeatherData(
    val cityName: String,
    val weatherDate: Int,
    val cloudsCoverage: Int,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temperature: Double,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val lastHourRainVolume: Double? = null,
    val lastThreeHourRainVolume: Double? = null,
    val lastHourSnowVolume: Double? = null,
    val lastThreeHourSnowVolume: Double? = null,
    val visibility: Int,
    val description: String? = null,
    val iconSrc: String? = null,
    val windSpeed: Double,
    val windDegree: Int,
) {

    companion object {
        fun fromOpenWeatherService(
            openWeatherResponse: OpenWeatherResponse,
            context: Context,
            iconUrlTransformer: IconUrlTransformer,
        ): WeatherData {

            return WeatherData(
                openWeatherResponse.name,
                openWeatherResponse.dt,
                openWeatherResponse.clouds.all,
                openWeatherResponse.main.feelsLike,
                openWeatherResponse.main.humidity,
                openWeatherResponse.main.pressure,
                openWeatherResponse.main.temp,
                openWeatherResponse.main.tempMax,
                openWeatherResponse.main.tempMin,
                openWeatherResponse.rain?.lastHourVolume,
                openWeatherResponse.rain?.lastThreeHourVolume,
                openWeatherResponse.rain?.lastHourVolume,
                openWeatherResponse.rain?.lastHourVolume,
                openWeatherResponse.visibility,
                openWeatherResponse.weather[0].description,
                iconUrlTransformer.transformIconIdToUrl(
                    context,
                    openWeatherResponse.weather[0].icon
                ),
                openWeatherResponse.wind.speed,
                openWeatherResponse.wind.degree
            )
        }
    }
}