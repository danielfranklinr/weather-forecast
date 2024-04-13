import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dfr.weather.R

@Composable
fun ErrorDialog(message: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Text(text = stringResource(R.string.activity_current_weather_error_dialog_title))
        }, text = {
            Text(message)
        },
            confirmButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(stringResource(id = R.string.activity_current_weather_dialog_btn_ok))
                }
            }
        )
    }
}


@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog("Test message")
}
