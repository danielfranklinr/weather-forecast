package com.dfr.weather.data.datasource

import android.content.Context
import com.dfr.weather.data.model.WeatherCityInformation
import com.dfr.weather.data.model.WeatherData
import com.dfr.weather.data.network.service.OpenWeatherApiService
import com.dfr.weather.data.network.transformer.IconUrlTransformer
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherProviderRemoteDatasourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherApiService: OpenWeatherApiService,
) : WeatherProviderRemoteDatasource {

    companion object {
        private const val MAX_RETRIES = 3
        private const val RETRY_DELAY_SECONDS = 3
    }

    override fun getWeatherByCoordinates(
        latitude: String,
        longitude: String,
        apiKey: String,
    ): Single<WeatherData> {
        return weatherApiService.getWeatherByCoordinates(
            latitude,
            longitude,
            apiKey
        ).subscribeOn(Schedulers.io())
            .retryWhen { error ->
                error
                    .zipWith(
                        Observable.range(1, MAX_RETRIES)
                    ) { throwable, attempt ->
                        Pair(throwable, attempt)
                    }
                    .flatMap { (throwable, attempt) ->
                        if (attempt <= MAX_RETRIES && throwable is HttpException) {
                            Observable.timer(
                                attempt.toLong() * RETRY_DELAY_SECONDS,
                                TimeUnit.SECONDS
                            )
                        } else {
                            Observable.error(throwable)
                        }
                    }
            }
            .map { weatherData ->
                WeatherData.fromOpenWeatherService(weatherData, context, IconUrlTransformer())
            }.singleOrError()
    }

    override fun getCityInformation(
        cityName: String,
        limit: Int,
        apiKey: String,
    ): Single<WeatherCityInformation> {
        return weatherApiService.getCityCoordinates(
            cityName,
            limit,
            apiKey,
        ).subscribeOn(Schedulers.io())
            .retryWhen { error ->
                error
                    .zipWith(
                        Observable.range(1, MAX_RETRIES)
                    ) { throwable, attempt ->
                        Pair(throwable, attempt)
                    }
                    .flatMap { (throwable, attempt) ->
                        if (attempt <= MAX_RETRIES && throwable is HttpException) {
                            Observable.timer(
                                attempt.toLong() * RETRY_DELAY_SECONDS,
                                TimeUnit.SECONDS
                            )
                        } else {
                            Observable.error(throwable)
                        }
                    }
            }
            .map { openWeatherCity ->
                openWeatherCity.first().run {
                    WeatherCityInformation.fromOpenWeatherCity(this)
                }
            }.singleOrError()
    }
}