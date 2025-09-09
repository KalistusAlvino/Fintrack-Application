package com.example.fintrack.presentation.main.home.transaction

sealed class TransactionEvent {
    data class PostIncome(
        val categoryId: Int,
        val amount: Long,
        val description: String
    ): TransactionEvent()

    data class PostExpenses(
        val categoryId: Int,
        val amount: Long,
        val description: String
    ): TransactionEvent()
}