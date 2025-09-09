package com.example.fintrack.di.model.Transaction

data class PostTransactionResponse(
    val walletId: Int? = null,
    val categoryId: String? = null,
    val description: String? = null,
    val date: String? = null,
    val amount: String? = null,
    val updatedAt: String? = null,
    val createdAt: String? = null,
    val id: Int? = null
)
