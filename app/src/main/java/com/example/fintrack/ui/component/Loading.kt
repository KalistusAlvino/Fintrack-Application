package com.example.fintrack.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState

@Composable
fun Loading() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.fintrack_loading
        )
    )
    val preLoaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preLoaderProgress
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    FintrackTheme {
        Loading()
    }
}