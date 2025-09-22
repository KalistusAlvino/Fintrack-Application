package com.example.fintrack.presentation.main.home.success

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.R
import com.example.fintrack.ui.component.SuccessTransaction
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SuccessTransactionScreen(
    navigateToMain: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BaseColor
                )
            )
        }
    ) { contentPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = BaseColor
                )
                .padding(contentPadding)
        ) {
            val screenHeight = maxHeight
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.height(screenHeight * 0.5f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SuccessTransaction()
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
                Text(
                    text = "Success!",
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.inter_extrabold_24pt)),
                    color = MainColor
                )
                Text(
                    text = "Your transaction has been\n recorded successfully.",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.inter_medium_24pt)),
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            navigateToMain()
                        },
                        modifier = Modifier.fillMaxWidth()
                            .padding(32.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainColor,
                            contentColor = BaseColor
                        )
                    ) {
                        Text(
                            text = "Continue",
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.inter_medium_24pt)),
                            color = BaseColor
                        )
                    }
                }

            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//private fun SuccessTransactionPreview() {
//    FintrackTheme {
//        SuccessTransactionScreen(
//            navigateToMain =
//        )
//    }
//}