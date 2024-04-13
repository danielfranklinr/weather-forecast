package com.dfr.weather.data.network.transformer

import android.content.Context
import com.dfr.weather.R

class IconUrlTransformer {
    fun transformIconIdToUrl(context: Context, iconId: String): String {

        val urlFormat = context.getString(R.string.service_open_weather_api_icon_url_format)

        return urlFormat.format(iconId)
    }
}