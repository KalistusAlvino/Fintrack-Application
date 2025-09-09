package com.example.fintrack.di.model.Transaction

data class GetMonthlySummaryResponse(
    val monthName: String,
    val monthKey: String,
    val total: Long,
    val formattedTotal: String,
)
