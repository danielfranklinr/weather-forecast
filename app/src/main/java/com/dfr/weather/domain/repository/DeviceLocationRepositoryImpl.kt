package com.dfr.weather.domain.repository

import com.dfr.weather.data.provider.DeviceLocationProvider
import com.dfr.weather.domain.model.DeviceCoordinatesDTO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DeviceLocationRepositoryImpl @Inject constructor(
    private val deviceLocationProvider: DeviceLocationProvider,
) : DeviceLocationRepository {

    override fun getDeviceLocation(): Single<DeviceCoordinatesDTO> {
        return deviceLocationProvider.getDeviceLocation().map {
            DeviceCoordinatesDTO.fromDeviceCoordinates(it)
        }
    }
}