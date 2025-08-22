package com.example.fintrack.data.remote.dto.income.get

data class IncomeCategoryDTO(
    val success: Boolean,
    val message: String,
    val data: List<DataIncomeCategoryDTO>
)
