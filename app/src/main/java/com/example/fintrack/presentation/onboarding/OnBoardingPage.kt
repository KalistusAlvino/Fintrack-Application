package com.example.fintrack.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.R
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor

@Composable
fun OnBoardingPage(
    onEvent: (OnBoardingEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding),
            contentDescription = "OnBoardingBackground",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 42.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Making your life easier",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Manage your income and expense in easiest \n" +
                        "way with this app",
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onEvent(OnBoardingEvent.saveOnBoardingStatus)
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 90.dp, end = 90.dp),
                colors = ButtonDefaults.buttonColors(MainColor)
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontSize = 16.sp,
                    color = BaseColor,
                )
            }
        }
    }
}

//@Preview(
//    widthDp = 360,
//    heightDp = 640
//)
//@Composable
//private fun OnBoardingPreview() {
//    FintrackTheme {
//        OnBoardingPage(
//            viewModel =
//        )
//    }
//}