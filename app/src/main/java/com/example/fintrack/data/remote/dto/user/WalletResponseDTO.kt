package com.example.fintrack.data.remote.dto.user

data class WalletResponseDTO(
    val success: Boolean? = null,
    val message: String? = null,
    val data: DataWalletResponseDTO? = null
)
