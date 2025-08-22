package com.example.fintrack.presentation.success

sealed class SuccessEvent {
    data class resendVerify(
        val email: String
    ) : SuccessEvent()
}