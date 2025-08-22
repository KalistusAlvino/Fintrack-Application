package com.example.fintrack.presentation.success

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.component.Mail
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.R
import com.example.fintrack.ui.component.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.theme.BlurDark

@Composable
fun SuccessScreen(
    onEvent: (SuccessEvent) -> Unit,
    email: String,
    navigateToLogin: () -> Unit,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val viewModel: SuccessViewModel = hiltViewModel()
    val result = viewModel.resendVerifyState.value
    val timeLeft by viewModel.countdown
    val isRunning by viewModel.isRunning

    BackHandler(enabled = true) {
        activity?.moveTaskToBack(true)
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                color = MainColor
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Box(
                modifier = Modifier.size(200.dp)
            ) {
                Mail()
            }
            Text(
                text = "Almost Done!",
                color = BaseColor,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 20.sp
            )
            Text(
                text = "Just one more step — check your email $email and verify your account to get started!",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                color = BaseColor,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            TextButton(
                timeLeft = timeLeft,
                isRunning = isRunning,
                onClick = {
                    onEvent(SuccessEvent.resendVerify(email))
                },
                text = "Didn't received email ?",
                buttonText = "Resend Code",
            )
            OutlinedButton(
                onClick = {
                    navigateToLogin()
                },
                modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .border(
                        width = 1.dp,
                        color = BaseColor,
                        shape = RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(BaseColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "User Icon",
                            tint = MainColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "Sign In",
                        color = BaseColor,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Text(
                text = "Already verified? Click the button to sign in.",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                color = BaseColor,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
    if (result.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BlurDark)
                .clickable { /* Prevent clicks */ },
            contentAlignment = Alignment.Center
        ) {
            Loading()
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun SuccessPreview() {
    FintrackTheme {
        val viewModel: SuccessViewModel = hiltViewModel()
        SuccessScreen(
            onEvent = viewModel::onEvent,
            email = "Kalistus33@gmail.com",
            navigateToLogin = {}
        )
    }
}