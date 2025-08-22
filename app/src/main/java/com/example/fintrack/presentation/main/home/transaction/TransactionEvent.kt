package com.example.fintrack.presentation.main.home.transaction

sealed class TransactionEvent {
    data class postIncome(
        val categoryId: Int,
        val amount: Long,
        val description: String
    ): TransactionEvent()
}