@file:OptIn(ExperimentalMaterial3Api::class)

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfr.weather.R
import com.dfr.weather.domain.model.WeatherDataDTO
import com.dfr.weather.presentation.ui.component.removeDecimals
import com.dfr.weather.presentation.viewmodel.CurrentWeatherViewModel


@Composable
fun WeatherInformationScreen(
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
    weatherDataDTO: WeatherDataDTO,
) {
    val isLandscape = isLandscape()
    if (isLandscape) {
        WeatherInformationLandscapeScreen(weatherDataDTO = weatherDataDTO)
    } else {
        WeatherInformationPortraitScreen(weatherDataDTO = weatherDataDTO)
    }
}


@Composable
private fun WeatherInformationPortraitScreen(
    modifier: Modifier = Modifier,
    weatherDataDTO: WeatherDataDTO,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        CurrentWeatherDetail(weatherDataDTO)
        Spacer(modifier = Modifier.padding(24.dp))
        WeatherDetailsGrid(weatherDataDTO)
    }
}

@Composable
private fun WeatherInformationLandscapeScreen(
    weatherDataDTO: WeatherDataDTO,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            CurrentWeatherDetail(weatherDataDTO)
        }
        Column(
            modifier = Modifier
                .weight(2f)
        ) {
            WeatherDetailsGrid(weatherDataDTO)
        }
    }
}

@Composable
fun WeatherDetailsGrid(weatherDataDTO: WeatherDataDTO) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        var columns = 2

        val screenWidth = LocalConfiguration.current.screenWidthDp

        if (200.dp.value < screenWidth && screenWidth < 400.dp.value) {
            columns = 3
        } else if (screenWidth >= 400.dp.value) {
            columns = 4
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Adjust spacing vertically
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            item {
                CurrentWeatherInfoCard(
                    "${removeDecimals(weatherDataDTO.feelsLike)} ${weatherDataDTO.temperatureUnit}",
                    stringResource(id = R.string.activity_current_weather_label_temperature_feeling)

                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${removeDecimals(weatherDataDTO.temperatureMax)} ${weatherDataDTO.temperatureUnit}",
                    stringResource(id = R.string.activity_current_weather_label_temperature_max)
                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${removeDecimals(weatherDataDTO.temperatureMin)} ${weatherDataDTO.temperatureUnit}",
                    stringResource(id = R.string.activity_current_weather_label_temperature_min)
                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${weatherDataDTO.humidity} ${weatherDataDTO.humidityUnit}",
                    stringResource(id = R.string.activity_current_weather_label_humidity)

                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${weatherDataDTO.pressure} ${weatherDataDTO.pressureUnit}",
                    stringResource(id = R.string.activity_current_weather_label_pressure)
                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${removeDecimals(weatherDataDTO.windSpeed)} ${weatherDataDTO.windSpeedUnit}",
                    stringResource(id = R.string.activity_current_weather_label_wind)

                )
            }
            item {

                CurrentWeatherInfoCard(
                    "${weatherDataDTO.visibility} ${weatherDataDTO.visibilityUnit}",
                    stringResource(id = R.string.activity_current_weather_label_visibility)
                )
            }


        }

    }
}

@Composable
private fun isLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

@Preview(
    showBackground = true,
    device = "spec:width=600dp,height=800dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Composable
fun WeatherInformationScreenPreview() {
    WeatherInformationScreen(
        weatherDataDTO = WeatherDataDTO(
            "Montevideo",
            1213123,
            "Saturday, May 5",
            11,
            34.0,
            80,
            "%",
            56,
            "hPa",
            27.5,
            "°C",
            36.0,
            18.0,
            null,
            11,
            "km",
            "Rainy day",
            "https://openweathermap.org/img/wn/10d@2x.png",
            22.0,
            "m/s",
            17
        )
    )
}
