package com.example.fintrack.data.remote.dto.auth

data class ResendVerifyResponseDTO(
    val success: Boolean? = null,
    val message: String? = null,
    val data: EmailFromResendVerifyDTO? = null
)