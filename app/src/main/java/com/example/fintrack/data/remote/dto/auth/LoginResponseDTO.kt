package com.example.fintrack.data.remote.dto.auth

data class LoginResponseDTO(
    val success: Boolean? = null,
    val message: String? = null,
    val data: DataLoginResponseDTO? = null
)
