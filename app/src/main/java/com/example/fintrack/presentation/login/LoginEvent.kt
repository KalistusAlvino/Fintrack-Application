package com.example.fintrack.presentation.login

import com.example.fintrack.presentation.onboarding.OnBoardingEvent
import com.example.fintrack.presentation.register.RegisterEvent

sealed class LoginEvent {
    data class Login(
        val username: String,
        val password: String,
    ): LoginEvent()
}