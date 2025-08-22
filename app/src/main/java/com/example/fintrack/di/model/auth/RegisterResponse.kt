package com.example.fintrack.di.model.auth


data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val email: String? = null,
)