package com.example.fintrack.presentation.main.home.success

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fintrack.ui.theme.FintrackTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SuccessTransactionScreen() {
    BoxWithConstraints (
        modifier = Modifier.fillMaxSize()
    ){
        val screenHeight = maxHeight
        Column (
            modifier = Modifier.fillMaxSize()
                .height(screenHeight * 0.5f)
        ){

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SuccessTransactionPreview() {
    FintrackTheme {
        SuccessTransactionScreen()
    }
}