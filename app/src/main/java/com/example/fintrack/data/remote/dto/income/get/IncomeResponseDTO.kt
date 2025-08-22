package com.example.fintrack.data.remote.dto.income.get


data class IncomeResponseDTO(
    val success: Boolean,
    val message: String,
    val data: List<DataIncomeResponseDTO>
)
