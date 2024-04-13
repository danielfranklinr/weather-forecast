package com.dfr.weather.presentation.state

import com.dfr.weather.domain.model.WeatherDataDTO


sealed class CurrentWeatherUIState {
    object Instructions : CurrentWeatherUIState()
    object Loading : CurrentWeatherUIState()
    class Loaded(val data: WeatherDataDTO) : CurrentWeatherUIState()
    class Error(val message: String) : CurrentWeatherUIState()
}