package com.example.fintrack.presentation.register

import com.example.fintrack.presentation.login.LoginEvent

sealed class RegisterEvent {
    data class Register(
        val username: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ): RegisterEvent()
}