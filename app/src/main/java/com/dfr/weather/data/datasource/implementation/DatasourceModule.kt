package com.dfr.weather.data.datasource.implementation

import android.content.Context
import com.dfr.weather.data.datasource.WeatherProviderRemoteDatasource
import com.dfr.weather.data.network.service.OpenWeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Singleton
    @Provides
    fun providesWeatherProviderRemoteDatasource(
        @ApplicationContext context: Context,
        weatherApiService: OpenWeatherApiService,
    ): WeatherProviderRemoteDatasource {
        return WeatherProviderRemoteDatasourceImpl(context, weatherApiService)
    }
}
