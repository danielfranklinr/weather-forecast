package com.dfr.weather.domain.model

import com.dfr.weather.data.model.DeviceCoordinates

data class DeviceCoordinatesDTO(
    val latitude: String? = null,
    val longitude: String? = null,
) {
    companion object {
        fun fromDeviceCoordinates(deviceCoordinates: DeviceCoordinates): DeviceCoordinatesDTO {
            return DeviceCoordinatesDTO(deviceCoordinates.latitude, deviceCoordinates.longitude)
        }
    }
}
