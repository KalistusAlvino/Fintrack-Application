package com.example.fintrack.domain.usecase.income.get

import androidx.paging.PagingData
import com.example.fintrack.data.remote.dto.base.BaseUseCase
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllIncomeUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
): BaseUseCase<Unit, Flow<PagingData<GetTransactionResponse>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<GetTransactionResponse>> {
        return transactionRepository.getAllIncome()
    }
}