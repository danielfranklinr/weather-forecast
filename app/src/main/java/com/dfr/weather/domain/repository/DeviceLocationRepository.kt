package com.dfr.weather.domain.repository

import com.dfr.weather.domain.model.DeviceCoordinatesDTO
import io.reactivex.rxjava3.core.Single

interface DeviceLocationRepository {
    fun getDeviceLocation(): Single<DeviceCoordinatesDTO>

}