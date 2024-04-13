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