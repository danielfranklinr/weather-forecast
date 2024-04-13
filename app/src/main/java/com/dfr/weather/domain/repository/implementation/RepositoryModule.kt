package com.dfr.weather.domain.repository.implementation

import android.content.Context
import com.dfr.weather.data.datasource.WeatherProviderRemoteDatasource
import com.dfr.weather.data.provider.DeviceLocationProvider
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
object RepositoryModule {
    @Singleton
    @Provides
    fun providesWeatherProviderRemoteRepository(
        @ApplicationContext context: Context,
        weatherProviderRemoteDatasource: WeatherProviderRemoteDatasource,
    ): WeatherProviderRemoteRepository {
        return WeatherProviderRemoteRepositoryImpl(context, weatherProviderRemoteDatasource)
    }

    @Singleton
    @Provides
    fun providesSearchableCitiesRepository(): SearchableCitiesRepository {
        return SearchableCitiesRepositoryImpl()
    }

    @Singleton
    @Provides
    fun providesDeviceLocationRepository(
        deviceLocationProvider: DeviceLocationProvider,
    ): DeviceLocationRepository {
        return DeviceLocationRepositoryImpl(deviceLocationProvider)
    }
}
