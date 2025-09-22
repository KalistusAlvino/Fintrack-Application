package com.example.fintrack.di.repository.transaction


import android.provider.SyncStateContract
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fintrack.core.common.toMonthlySummaryResponse
import com.example.fintrack.core.common.toTransactionCategoryResponse
import com.example.fintrack.core.common.toTransactionResponse
import com.example.fintrack.core.common.toPostTransactionResponse
import com.example.fintrack.core.common.toThisMonthIncomeResponse
import com.example.fintrack.data.datasource.TransactionRemoteDataSource
import com.example.fintrack.data.paging.ExpensesPagingSource
import com.example.fintrack.data.paging.IncomePagingSource
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.di.model.Transaction.GetThisMonthSummaryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.di.model.Transaction.PostTransactionResponse
import com.example.fintrack.domain.di.transaction.TransactionRepository
import com.example.fintrack.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val fintrackApi: FintrackApi,
    private val transactionRemoteDataSource: TransactionRemoteDataSource
) : TransactionRepository {
    override suspend fun getIncome(): List<GetTransactionResponse> {
        return fintrackApi.getIncome().data?.map { it.toTransactionResponse() } ?: emptyList()
    }

    override suspend fun getExpenses(): List<GetTransactionResponse> {
        return fintrackApi.getExpenses().data?.map { it.toTransactionResponse() } ?: emptyList()
    }

    override suspend fun getExpensesCategory(): List<GetTransactionCategoryResponse> {
        return fintrackApi.getExpensesCategory().data?.map { it.toTransactionCategoryResponse() }
            ?: emptyList()
    }

    override suspend fun getIncomeCategory(): List<GetTransactionCategoryResponse> {
        return fintrackApi.getIncomeCategory().data?.map { it.toTransactionCategoryResponse() }
            ?: emptyList()
    }

    override suspend fun getThisMonthIncome(): GetThisMonthSummaryResponse {
        return fintrackApi.thisMonthIncome().data?.toThisMonthIncomeResponse() ?: throw Exception("No response")
    }
    override suspend fun getThisMonthExpenses(): GetThisMonthSummaryResponse {
        return fintrackApi.thisMonthExpenses().data?.toThisMonthIncomeResponse() ?: throw Exception("No response")
    }

    override suspend fun getMonthlyIncome(): List<GetMonthlySummaryResponse> {
        return fintrackApi.monthlyIncome().data?.map { it.toMonthlySummaryResponse() } ?: emptyList()
    }

    override suspend fun getMonthlyExpenses(): List<GetMonthlySummaryResponse> {
        return fintrackApi.monthlyExpenses().data?.map { it.toMonthlySummaryResponse() } ?: emptyList()
    }

    override suspend fun getAllIncome(): Flow<PagingData<GetTransactionResponse>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGING_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                IncomePagingSource(transactionRemoteDataSource)
            }
        ).flow
    }

    override suspend fun getAllExpenses(): Flow<PagingData<GetTransactionResponse>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGING_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                ExpensesPagingSource(transactionRemoteDataSource)
            }
        ).flow
    }

    override suspend fun postIncome(
        categoryId: Int,
        amount: Long,
        description: String
    ): PostTransactionResponse {
        return fintrackApi.postIncome(
            categoryId,
            amount,
            description
        ).data?.toPostTransactionResponse() ?: throw Exception("No response")
    }

    override suspend fun postExpenses(
        categoryId: Int,
        amount: Long,
        description: String
    ): PostTransactionResponse {
        return fintrackApi.postExpenses(
            categoryId,
            amount,
            description
        ).data?.toPostTransactionResponse() ?: throw Exception("No response")
    }
}