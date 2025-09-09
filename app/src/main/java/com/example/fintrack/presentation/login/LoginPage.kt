package com.example.fintrack.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.R
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.component.PasswordTextField
import com.example.fintrack.ui.component.TextField
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.BlurDark
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.ui.theme.MainColor


@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    onEvent: (LoginEvent) -> Unit,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val result = viewModel.loginState.value

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusRequesterPassword = remember { FocusRequester() }

    val isFilled = username.isNotBlank() && password.isNotBlank()

    Scaffold() { contentPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            val screenHeight = maxHeight
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentPadding = PaddingValues(bottom = 56.dp), // ruang untuk bottom content
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    ElevatedCard(
                        shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = BaseColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.5f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.login_asset),
                                contentDescription = "Landing page image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(horizontal = 16.dp),
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 32.dp, vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Welcome Back to Fintrack!",
                                    color = MainColor,
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Track your expenses, save smarter, and stay on top of your financial goals",
                                    modifier = Modifier.padding(top = 8.dp),
                                    color = MainColor,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            contentAlignment = Alignment.Center
                        ){
                            if (result.success == false) {
                                Text(
                                    text = result.message.toString(),
                                    fontSize = 14.sp,
                                    color = Color.Red,
                                    fontFamily = FontFamily(Font(R.font.inter_medium_24pt)),
                                )
                            }
                        }
                        TextField(
                            label = "Username",
                            value = username,
                            onValueChange = { username = it },
                            leadingIcon = Icons.Default.Person,
                            placeholder = "Enter your username",
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                autoCorrectEnabled = true
                            ),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = BaseColor
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusRequesterPassword.requestFocus() }
                            ),
                        )
                        PasswordTextField(
                            label = "Password",
                            modifier = Modifier.focusRequester(focusRequesterPassword),
                            value = password,
                            onValueChange = { password = it },
                            leadingIcon = Icons.Default.Lock,
                            placeholder = "Enter your password",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next,
                                autoCorrectEnabled = false
                            ),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = BaseColor
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { }
                            )
                        )
                        OutlinedButton(
                            onClick = {
                                onEvent(LoginEvent.Login(username, password))
                            },
                            enabled = isFilled,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .fillMaxWidth()
                                .height(52.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isFilled) BaseColor else Color.Gray
                            )
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
                                        imageVector = Icons.Filled.KeyboardArrowRight,
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
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Didn't have an account?",
                            color = BaseColor,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                        TextButton(
                            modifier = Modifier.padding(start = 0.dp),
                            onClick = {
                                navigateToRegister()
                            }
                        ) {
                            Text(
                                text = "Sign Up",
                                color = BaseColor,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold))
                            )
                        }
                    }
                }
            }
        }
    }
    result.data?.let {
        if (result.success) {
            navigateToHome()
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
    heightDp = 720
)
@Composable
private fun LoginPagePreview() {
    FintrackTheme {
        LoginPage(
            navigateToRegister = {},
            onEvent = {},
            navigateToHome = {}
        )
    }
}