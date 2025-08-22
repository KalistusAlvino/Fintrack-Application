package com.example.fintrack.data.remote.dto.income.get

data class ThisMonthIncomeDTO(
    val success: Boolean,
    val message: String,
    val data: DataThisMonthIncomeDTO
)
