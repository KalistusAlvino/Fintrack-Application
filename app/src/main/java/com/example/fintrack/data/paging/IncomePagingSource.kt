package com.example.fintrack.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fintrack.core.common.toTransactionResponse
import com.example.fintrack.data.datasource.TransactionRemoteDataSource
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import retrofit2.HttpException
import java.io.IOException

class IncomePagingSource(
    private val transactionRemoteDataSource: TransactionRemoteDataSource
): PagingSource<Int, GetTransactionResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetTransactionResponse> {
        return try {
            val currentPage = params.key ?: 1
            val transaction = transactionRemoteDataSource.getAllIncome(pageNumber = currentPage)
            LoadResult.Page(
                data = transaction.data!!.map { it.toTransactionResponse() },
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = if (transaction.data.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetTransactionResponse>): Int? {
        return state.anchorPosition
    }
}