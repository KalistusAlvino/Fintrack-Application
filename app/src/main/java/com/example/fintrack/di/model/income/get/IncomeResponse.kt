package com.example.fintrack.di.model.income.get


data class IncomeResponse(
    val id: Int? = null,
    val name: String,
    val images: String,
    val description: String,
    val date: String,
    val amount: Long? = null,
    val formattedAmount: String,
)
