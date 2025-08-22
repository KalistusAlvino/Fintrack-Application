package com.example.fintrack.di.model.auth

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val id: Int? = null,
    val username: String? = null,
    val token: String? = null
)
