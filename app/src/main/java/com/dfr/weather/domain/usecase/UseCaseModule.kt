package com.dfr.weather.domain.usecase

import android.content.Context
import com.dfr.weather.domain.repository.DeviceLocationRepository
import com.dfr.weather.domain.repository.SearchableCitiesRepository
import com.dfr.weather.domain.repository.WeatherProviderRemoteRepository
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
    fun providesGetCityCoordinatesUseCase(
        @ApplicationContext context: Context,
        weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
    ): GetCityCoordinatesUseCase {
        return GetCityCoordinatesUseCaseImpl(context, weatherProviderRemoteRepository)
    }

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
    fun providesGetCurrentWeatherByCoordinatesUseCase(
        @ApplicationContext context: Context,
        weatherProviderRemoteRepository: WeatherProviderRemoteRepository,
    ): GetCurrentWeatherByCoordinatesUseCase {
        return GetCurrentWeatherByCoordinatesUseCaseImpl(context, weatherProviderRemoteRepository)
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
