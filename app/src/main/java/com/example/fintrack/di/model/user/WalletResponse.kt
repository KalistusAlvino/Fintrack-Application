package com.example.fintrack.di.model.user

data class WalletResponse(
    val success: Boolean,
    val message: String,
    val id: Int? = null,
    val username: String? = null,
    val balance: String? = null
)
