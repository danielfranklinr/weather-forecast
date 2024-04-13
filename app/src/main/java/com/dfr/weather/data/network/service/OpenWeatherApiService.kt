package com.dfr.weather.data.network.service

import com.dfr.weather.data.network.service.implementation.response.OpenWeatherCity
import com.dfr.weather.data.network.service.implementation.response.OpenWeatherResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("/data/2.5/weather")
    fun getWeatherByCoordinates(
        @Query(value = "lat") latitude: String,
        @Query(value = "lon") longitude: String,
        @Query(value = "appid") apiKey: String,
        @Query(value = "units") unit: String = "metric",
    ): Observable<OpenWeatherResponse>

    @GET("/geo/1.0/direct")
    fun getCityCoordinates(
        @Query(value = "q") cityName: String,
        @Query(value = "limit") longitude: Int = 1,
        @Query(value = "appid") apiKey: String,
        @Query(value = "units") unit: String = "metric",
    ): Observable<List<OpenWeatherCity>>
}