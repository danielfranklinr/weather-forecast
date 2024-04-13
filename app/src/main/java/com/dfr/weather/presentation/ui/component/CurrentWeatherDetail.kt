import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dfr.weather.domain.model.WeatherDataDTO
import com.dfr.weather.presentation.ui.component.removeDecimals

@Composable
fun CurrentWeatherDetail(weatherDataDTO: WeatherDataDTO) {
    Column() {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = weatherDataDTO.cityName,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Text(
            text = weatherDataDTO.weatherDateFormatted,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(fontSize = 16.sp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(40.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = weatherDataDTO.iconUrl,
                    contentDescription = "weather icon",
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = removeDecimals(doubleValue = weatherDataDTO.temperature),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 30.sp
                        )
                    )

                    Text(
                        text = weatherDataDTO.temperatureUnit,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 22.sp
                        ),
                    )
                }

                Text(
                    text = weatherDataDTO.description.orEmpty(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 20.sp,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CurrentWeatherDetailPreview() {

    CurrentWeatherDetail(
        WeatherDataDTO(
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
