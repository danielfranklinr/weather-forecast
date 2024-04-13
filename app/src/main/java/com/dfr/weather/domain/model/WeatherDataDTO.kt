package com.dfr.weather.domain.model

import android.content.Context
import com.dfr.weather.R
import com.dfr.weather.data.model.WeatherData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

data class WeatherDataDTO(
    val cityName: String,
    val weatherDateMillis: Long,
    val weatherDateFormatted: String,
    val cloudsCoverage: Int,
    val feelsLike: Double,
    val humidity: Int,
    val humidityUnit: String,
    val pressure: Int,
    val pressureUnit: String,
    val temperature: Double,
    val temperatureUnit: String,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val lastHourRainVolume: Double? = null,
    val visibility: Int,
    val visibilityUnit: String,
    val description: String? = null,
    val iconUrl: String? = null,
    val windSpeed: Double,
    val windSpeedUnit: String,
    val windDegree: Int,
) {

    companion object {
        fun fromWeatherData(context: Context, weatherData: WeatherData): WeatherDataDTO {

            val millisTime = transformEpochTimeToMillis(weatherData.weatherDate)
            val weatherDateFormatted = formatDate(context, millisTime)
            val temperatureUnit = getTemperatureUnit(context)

            return WeatherDataDTO(
                weatherData.cityName,
                millisTime,
                weatherDateFormatted,
                weatherData.cloudsCoverage,
                weatherData.feelsLike,
                weatherData.humidity,
                getHumidityUnit(context),
                weatherData.pressure,
                getPressureUnit(context),
                weatherData.temperature,
                temperatureUnit,
                weatherData.temperatureMax,
                weatherData.temperatureMin,
                weatherData.lastHourRainVolume,
                transformMetersToKm(weatherData.visibility),
                getVisibilityUnit(context),
                weatherData.description,
                weatherData.iconSrc,
                transformWindSpeedToKmPerHour(weatherData.windSpeed),
                getWindSpeedUnit(context),
                weatherData.windDegree
            )
        }

        private fun transformEpochTimeToMillis(weatherDate: Int): Long = weatherDate.toLong() * 1000
        private fun transformMetersToKm(meters: Int): Int = meters / 1000
        private fun transformWindSpeedToKmPerHour(metersPerSecond: Double): Double =
            metersPerSecond * 3.6

        private fun formatDate(
            context: Context,
            timeMillis: Long,
        ): String {
            val dateFormat = context.getString(R.string.activity_current_weather_date_format)
            val dateFormatter = SimpleDateFormat(dateFormat, Locale.getDefault())
            val date = Date()
            date.time = timeMillis
            return dateFormatter.format(date).toString()
        }

        private fun getTemperatureUnit(
            context: Context,
        ): String {
            return context.getString(R.string.activity_current_weather_label_temperature_unit_metric)
        }

        private fun getHumidityUnit(
            context: Context,
        ): String {
            return context.getString(R.string.activity_current_weather_label_humidity_unit_metric)
        }

        private fun getVisibilityUnit(
            context: Context,
        ): String {
            return context.getString(R.string.activity_current_weather_label_visibility_unit_metric)
        }

        private fun getPressureUnit(
            context: Context,
        ): String {
            return context.getString(R.string.activity_current_weather_label_pressure_unit_metric)
        }

        private fun getWindSpeedUnit(
            context: Context,
        ): String {
            return context.getString(R.string.activity_current_weather_label_wind_speed_unit_metric)
        }
    }
}