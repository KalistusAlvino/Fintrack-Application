package com.example.fintrack.presentation.register

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.R
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.component.PasswordTextField
import com.example.fintrack.ui.component.TextField
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.BlurDark
import com.example.fintrack.ui.theme.MainColor

@Composable
fun RegisterPage(
    onEvent: (RegisterEvent) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToSuccess: (email: String) -> Unit
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val result = viewModel.registerState.value

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterConfirmPassword = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor),
            contentPadding = PaddingValues(bottom = 56.dp), // ruang untuk bottom content
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ElevatedCard(
                    shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = BaseColor),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.register_asset),
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
                                text = "Join Fintrack!",
                                color = MainColor,
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Start your journey to smarter spending and better saving. Sign up now!",
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
                            onNext = { focusRequesterEmail.requestFocus() }
                        ),
                    )
                    TextField(
                        modifier = Modifier.focusRequester(focusRequesterEmail),
                        label = "Email",
                        value = email,
                        onValueChange = { email = it },
                        leadingIcon = Icons.Default.Email,
                        placeholder = "Enter your Email",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next,
                            autoCorrectEnabled = false
                        ),
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = BaseColor
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusRequesterPassword.requestFocus() }
                        )
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
                            onNext = { focusRequesterConfirmPassword.requestFocus() }
                        )
                    )
                    PasswordTextField(
                        label = "Confirm password",
                        modifier = Modifier.focusRequester(focusRequesterConfirmPassword),
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        leadingIcon = Icons.Default.Lock,
                        placeholder = "Enter your confirm password",
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = BaseColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                            autoCorrectEnabled = false
                        )
                    )
                    OutlinedButton(
                        onClick = {
                            onEvent(RegisterEvent.Register(username,email,password,confirmPassword))
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .height(52.dp)
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
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "User Icon",
                                    tint = MainColor,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Text(
                                text = "Sign Up",
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
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Already have an account?",
                        color = BaseColor,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    TextButton(
                        modifier = Modifier.padding(start = 0.dp),
                        onClick = {
                            navigateToLogin()
                        }
                    ) {
                        Text(
                            text = "Sign In",
                            color = BaseColor,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                }
            }
        }
    }
    result.data?.let { data ->
        if (result.success) {
            Log.d("RegisterScreen", data.email.toString())
            navigateToSuccess(data.email.toString())
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

//@Preview(
//    widthDp = 360,
//    heightDp = 640
//)
//@Composable
//private fun RegisterPagePreview() {
//    FintrackTheme {
//        RegisterPage()
//    }
//}