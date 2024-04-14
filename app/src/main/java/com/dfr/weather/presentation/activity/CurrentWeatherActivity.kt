@file:OptIn(ExperimentalMaterial3Api::class)

package com.dfr.weather.presentation.activity

import ErrorDialog
import InstructionsComponent
import LoadingScreen
import WeatherInformationScreen
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfr.weather.R
import com.dfr.weather.presentation.state.CurrentWeatherUIState
import com.dfr.weather.presentation.ui.theme.WeatherTheme
import com.dfr.weather.presentation.viewmodel.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CurrentWeatherScreenContainer()
                }
            }
        }
    }

    private fun requestLocationPermission() {
        val fineLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!fineLocationPermissionGranted || !coarseLocationPermissionGranted) {
            val permissionsToRequest = mutableListOf<String>()
            if (!fineLocationPermissionGranted) {
                permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            if (!coarseLocationPermissionGranted) {
                permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }
}


@Composable
fun CurrentWeatherScreenContainer(mainViewModel: CurrentWeatherViewModel = hiltViewModel()) {
    val searchableCities = mainViewModel.getSearchableCities()
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface)
        ) {
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {
                    expanded = !expanded
                },
                modifier = Modifier
                    .weight(3f)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                TextField(
                    value = searchableCities[selectedIndex].name,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    searchableCities.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item.name) },
                            onClick = {
                                selectedIndex = searchableCities.indexOf(item)
                                expanded = false
                                mainViewModel.getCityWeather(item.name)
                            }
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )

            Button(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                onClick = {
                    mainViewModel.getMyLocationWeather()
                }) {
                Text(
                    text = stringResource(id = R.string.activity_current_weather_btn_my_location)
                )
            }

        }

        // Button to get weather

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = mainViewModel.uiState.collectAsState().value) {
            is CurrentWeatherUIState.Instructions -> InstructionsComponent()
            is CurrentWeatherUIState.Loading -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingScreen()
            }

            is CurrentWeatherUIState.Error -> ErrorDialog(state.message)
            is CurrentWeatherUIState.Loaded -> WeatherInformationScreen(
                weatherDataDTO = state.data
            )
        }
    }

}


