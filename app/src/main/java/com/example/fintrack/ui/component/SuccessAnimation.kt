package com.example.fintrack.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fintrack.R
import com.example.fintrack.ui.theme.FintrackTheme

@Composable
fun SuccessTransaction() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.success_transaction
        )
    )
    val preLoaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
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
        SuccessTransaction()
    }
}