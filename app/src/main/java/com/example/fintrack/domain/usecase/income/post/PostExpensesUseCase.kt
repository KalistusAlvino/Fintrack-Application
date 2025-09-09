package com.example.fintrack.domain.usecase.income.post

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.data.remote.dto.transaction.PostTransactionDTO
import com.example.fintrack.di.model.Transaction.PostTransactionResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class PostExpensesUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(
        categoryId: Int,
        amount: Long,
        description: String
    ): Flow<ResultState<PostTransactionResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = transactionRepository.postExpenses(categoryId, amount, description)
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, PostTransactionDTO::class.java)
            emit(ResultState.Error(message = errorResponse.toString()))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}