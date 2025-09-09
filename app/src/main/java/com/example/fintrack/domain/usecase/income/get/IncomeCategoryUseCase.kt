package com.example.fintrack.domain.usecase.income.get

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class IncomeCategoryUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
){
    operator fun invoke(): Flow<ResultState<List<GetTransactionCategoryResponse>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = transactionRepository.getIncomeCategory()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            emit(ResultState.Error(message = e.message.toString()))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message.toString()))
        }
    }
}