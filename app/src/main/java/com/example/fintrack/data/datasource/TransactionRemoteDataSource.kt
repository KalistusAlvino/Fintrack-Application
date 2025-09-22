package com.example.fintrack.data.datasource

import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.data.remote.dto.transaction.GetTransactionDTO
import com.example.fintrack.di.model.Transaction.GetTransactionResponse

interface TransactionRemoteDataSource {
    suspend fun getAllIncome(
        pageNumber: Int
    ): BaseResponse<List<GetTransactionDTO>>

    suspend fun getAllExpenses(
        pageNumber: Int
    ): BaseResponse<List<GetTransactionDTO>>
}