package com.example.fintrack.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.R
import com.example.fintrack.ui.theme.BaseColor
import java.util.Locale

@Composable
fun TextButton(
    timeLeft: Int,
    isRunning: Boolean,
    modifier: Modifier = Modifier,
    text: String,
    buttonText: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    Box(
        modifier = modifier.fillMaxWidth()
            .height(24.dp)
    )
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (isRunning && timeLeft > 0) {
            Text(
                text = formattedTime,
                color = BaseColor,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        } else {
            Text(
                text = text,
                color = BaseColor,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            TextButton(
                modifier = Modifier.padding(start = 0.dp),
                onClick = {
                    onClick()
                },
                enabled = enabled
            ) {
                Text(
                    text = buttonText,
                    color = BaseColor,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                )
            }
        }

    }
}

@Preview
@Composable
private fun TextButtonPreview() {

}