package com.dfr.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.dfr.weather.domain.model.SearchableCityDTO
import com.dfr.weather.domain.usecase.GetCurrentWeatherByCityNameUseCase
import com.dfr.weather.domain.usecase.GetMyLocationCurrentWeatherUseCase
import com.dfr.weather.domain.usecase.GetSearchableCitiesUseCase
import com.dfr.weather.presentation.state.CurrentWeatherUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getSearchableCitiesUseCase: GetSearchableCitiesUseCase,
    private val getCurrentWeatherByCityNameUseCase: GetCurrentWeatherByCityNameUseCase,
    private val getMyLocationCurrentWeatherUseCase: GetMyLocationCurrentWeatherUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CurrentWeatherUIState>(CurrentWeatherUIState.Loading)
    val uiState: StateFlow<CurrentWeatherUIState> = _uiState

    private var disposable: Disposable? = null


    fun getCityWeather(cityName: String) {
        _uiState.value = CurrentWeatherUIState.Loading

        disposable?.dispose()
        disposable = getCurrentWeatherByCityNameUseCase.getCurrentWeatherByCityName(cityName)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { weatherData ->
                    _uiState.value = CurrentWeatherUIState.Loaded(weatherData)
                },
                { throwable ->
                    _uiState.value = CurrentWeatherUIState.Error(
                        throwable.message ?: "An error occurred trying to retrieve the data"
                    )
                }
            )
    }

    fun getMyLocationWeather() {
        _uiState.value = CurrentWeatherUIState.Loading

        disposable?.dispose()
        disposable = getMyLocationCurrentWeatherUseCase.getMyLocationCurrentWeather()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { weatherData ->
                    _uiState.value = CurrentWeatherUIState.Loaded(weatherData)
                },
                { throwable ->
                    _uiState.value = CurrentWeatherUIState.Error(
                        throwable.message ?: "An error occurred trying to retrieve the data"
                    )
                }
            )
    }

    fun getSearchableCities(): List<SearchableCityDTO> {
        return getSearchableCitiesUseCase.getCitiesList().blockingGet()
    }
}