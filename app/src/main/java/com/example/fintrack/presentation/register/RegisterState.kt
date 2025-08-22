package com.example.fintrack.presentation.register

import com.example.fintrack.di.model.auth.RegisterResponse

data class RegisterState (
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: RegisterResponse? = null,
    var message: String = ""
)