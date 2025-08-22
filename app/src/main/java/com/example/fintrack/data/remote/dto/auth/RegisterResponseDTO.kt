package com.example.fintrack.data.remote.dto.auth

data class RegisterResponseDTO(
    val success: Boolean? = null,
    val message: String? = null,
    val data: EmailFromRegisterDTO? = null
)
