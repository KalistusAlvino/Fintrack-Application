package com.example.fintrack.presentation.login

import com.example.fintrack.di.model.auth.LoginResponse

data class LoginState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: LoginResponse? = null,
    var message: String = ""
)
