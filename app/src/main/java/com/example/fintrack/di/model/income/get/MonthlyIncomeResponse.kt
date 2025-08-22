package com.example.fintrack.di.model.income.get


data class MonthlyIncomeResponse(
    val monthName: String,
    val monthKey: String,
    val totalIncome: Long,
    val formattedIncome: String,
)
