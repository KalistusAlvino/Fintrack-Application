package com.example.fintrack.presentation.success

import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.di.model.auth.ResendVerifiyResponse

data class SuccessState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: ResendVerifiyResponse? = null,
    var message: String = ""
)
