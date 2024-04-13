package com.dfr.weather.domain.usecase.implementation

import android.content.Context
import com.dfr.weather.domain.repository.DeviceLocationRepository
import com.dfr.weather.domain.repository.SearchableCitiesRepository
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
import com.dfr.weather.domain.usecase.GetCurrentWeatherByCityNameUseCase
import com.dfr.weather.domain.usecase.GetMyLocationCurrentWeatherUseCase
import com.dfr.weather.domain.usecase.GetSearchableCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Singleton
    @Provides
    fun providesGetCurrentWeatherByCityNameUseCase(
        @ApplicationContext context: Context,
        weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
    ): GetCurrentWeatherByCityNameUseCase {
        return GetCurrentWeatherByCityNameUseCaseImpl(context, weatherProviderRemoteRepository)
    }


    @Singleton
    @Provides
    fun providesGetSearchableCitiesUseCase(
        getSearchableCitiesRepository: SearchableCitiesRepository,
    ): GetSearchableCitiesUseCase {
        return GetSearchableCitiesUseCaseImpl(getSearchableCitiesRepository)
    }

    @Singleton
    @Provides
    fun providesGetMyLocationCurrentWeatherUseCase(
        @ApplicationContext context: Context,
        deviceLocationRepository: DeviceLocationRepository,
        weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
    ): GetMyLocationCurrentWeatherUseCase {
        return GetMyLocationCurrentWeatherUseCaseImpl(
            context,
            deviceLocationRepository,
            weatherProviderRemoteRepository
        )
    }
}
