package com.dfr.weather.data.provider

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log

class LocationListener(private val context: Context) : LocationListener {

    override fun onLocationChanged(location: Location) {
        // Called when a new location is found
        Log.d("Location", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // Called when the provider status changes
    }

    override fun onProviderEnabled(provider: String) {
        // Called when the provider is enabled by the user
    }

    override fun onProviderDisabled(provider: String) {
        // Called when the provider is disabled by the user
    }
}