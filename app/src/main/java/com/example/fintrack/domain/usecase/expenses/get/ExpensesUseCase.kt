package com.example.fintrack.domain.usecase.expenses.get

import coil.network.HttpException
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExpensesUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<ResultState<List<GetTransactionResponse>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = transactionRepository.getExpenses()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException){
            emit(ResultState.Error(message = e.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }
}