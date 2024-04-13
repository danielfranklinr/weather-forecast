package com.dfr.weather.data.provider.implementation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.dfr.weather.data.model.DeviceCoordinates
import com.dfr.weather.data.provider.DeviceLocationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DeviceLocationProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DeviceLocationProvider {

    companion object {
        private const val MINIMUM_TIME_BETWEEN_UPDATES = 10000L //milliseconds
        private const val MINIMUM_DISTANCE_BETWEEN_UPDATES = 1000F // meters
        var locationListener: LocationListener? = null
    }

    override fun getDeviceLocation(): Single<DeviceCoordinates> {
        return Single.create { emitter ->
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    locationManager.removeUpdates(locationListener!!)
                    emitter.onSuccess(
                        DeviceCoordinates(
                            location.latitude.toString(),
                            location.longitude.toString()
                        )
                    )
                }

                override fun onLocationChanged(locations: MutableList<Location>) {
                    super.onLocationChanged(locations)
                }

                override fun onFlushComplete(requestCode: Int) {
                    super.onFlushComplete(requestCode)
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    super.onStatusChanged(provider, status, extras)
                }

                override fun onProviderEnabled(provider: String) {
                    super.onProviderEnabled(provider)
                }

                override fun onProviderDisabled(provider: String) {
                    super.onProviderDisabled(provider)
                }
            }

            // Request location updates using GPS provider
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                emitter.onError(Throwable("Please, activate GPS permissions"))
            } else {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MINIMUM_TIME_BETWEEN_UPDATES,
                    MINIMUM_DISTANCE_BETWEEN_UPDATES,
                    locationListener!!,
                    Looper.getMainLooper()
                )
            }
        }
    }
}