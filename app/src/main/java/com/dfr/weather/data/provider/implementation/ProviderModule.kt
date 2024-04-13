package com.dfr.weather.data.provider.implementation

import android.content.Context
import com.dfr.weather.data.provider.DeviceLocationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {
    @Singleton
    @Provides
    fun providesDeviceLocationProvider(
        @ApplicationContext context: Context,
    ): DeviceLocationProvider {
        return DeviceLocationProviderImpl(context)
    }
}
