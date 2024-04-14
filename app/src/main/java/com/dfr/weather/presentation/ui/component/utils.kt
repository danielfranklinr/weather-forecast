package com.dfr.weather.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dfr.weather.R

@Composable
fun removeDecimals(doubleValue: Double): String {
    return String.format(
        stringResource(id = R.string.activity_current_weather_info_card_decimal_format),
        doubleValue
    )
}

@Composable
fun transformDegreesToDirection(degree: Int): String {
    return when (degree) {
        0 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_north)
        in 1..89 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_north_east)
        90 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_east)
        in 91..179 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_south_east)
        180 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_south)
        in 181..269 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_south_west)
        270 -> stringResource(id = R.string.activity_current_weather_label_wind_direction_west)
        else -> stringResource(id = R.string.activity_current_weather_label_wind_direction_north_west)
    }
}