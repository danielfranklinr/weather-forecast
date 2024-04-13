package com.dfr.weather.data.provider

import com.dfr.weather.data.model.DeviceCoordinates
import io.reactivex.rxjava3.core.Single

interface DeviceLocationProvider {

    fun getDeviceLocation(
    ): Single<DeviceCoordinates>

}