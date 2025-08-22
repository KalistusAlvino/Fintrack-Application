package com.example.fintrack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable



@Composable
fun FintrackTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}