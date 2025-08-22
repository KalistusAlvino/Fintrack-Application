package com.example.fintrack.presentation.main.statistic

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fintrack.ui.theme.BaseColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun StatisticPage(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseColor.copy(0.9f))
    ) {

    }
}