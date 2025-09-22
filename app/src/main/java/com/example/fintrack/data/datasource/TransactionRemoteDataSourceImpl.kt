package com.example.fintrack.data.datasource

import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.data.remote.dto.transaction.GetTransactionDTO
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import javax.inject.Inject

class TransactionRemoteDataSourceImpl @Inject constructor(
    private val fintrackApi: FintrackApi
) : TransactionRemoteDataSource{
    override suspend fun getAllIncome(pageNumber: Int): BaseResponse<List<GetTransactionDTO>> {
        return fintrackApi.getAllIncome(pageNumber = pageNumber)
    }

    override suspend fun getAllExpenses(pageNumber: Int): BaseResponse<List<GetTransactionDTO>> {
        return fintrackApi.getAllExpenses(pageNumber = pageNumber)
    }

}