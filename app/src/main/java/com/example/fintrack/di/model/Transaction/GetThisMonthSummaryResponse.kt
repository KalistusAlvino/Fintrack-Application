package com.example.fintrack.di.model.Transaction

data class GetThisMonthSummaryResponse(
    val amount: Long,
    val formattedAmount: String
)
