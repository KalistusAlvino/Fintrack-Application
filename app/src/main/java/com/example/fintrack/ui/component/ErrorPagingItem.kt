package com.example.fintrack.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.R
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.MainColor

@Composable
fun ErrorPagingItem(
    message: String, onRetry: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AlertColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = null,
                tint = BaseColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = message,
                color = BaseColor,
                fontFamily = FontFamily(Font(R.font.inter_medium_24pt)),
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BaseColor,
                    contentColor = MainColor
                )
            ) {
                Text("Retry")
            }
        }
    }
}

@Preview
@Composable
private fun ErrorPagingItemPreview() {
    FintrackTheme {
        ErrorPagingItem(
            message = "Unable to load incomes",
            onRetry = {}
        )
    }
}
