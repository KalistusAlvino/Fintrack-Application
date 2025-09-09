package com.example.fintrack.di.model.Transaction



data class GetTransactionResponse(
    val id: Int,
    val name: String,
    val images: String,
    val description: String,
    val date: String,
    val amount: Long,
    val formattedAmount: String,
)
