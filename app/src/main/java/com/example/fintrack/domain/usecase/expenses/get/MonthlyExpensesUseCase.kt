package com.example.fintrack.domain.usecase.expenses.get

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MonthlyExpensesUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(
    ): Flow<ResultState<List<GetMonthlySummaryResponse>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = transactionRepository.getMonthlyExpenses()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            emit(ResultState.Error(message = e.message.toString()))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message.toString()))
        }
    }
}