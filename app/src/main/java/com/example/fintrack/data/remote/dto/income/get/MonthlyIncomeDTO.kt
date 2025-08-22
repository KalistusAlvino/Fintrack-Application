package com.example.fintrack.data.remote.dto.income.get

data class MonthlyIncomeDTO(
    val success: Boolean,
    val message: String,
    val data: List<DataMonthlyIncomeDTO>
)
