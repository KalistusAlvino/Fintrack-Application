package com.example.fintrack.domain.usecase.user

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.data.pref.UserPreference
import com.example.fintrack.di.model.user.WalletResponse
import com.example.fintrack.domain.di.user.WalletRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class WalletUseCase @Inject constructor(
    private val walletRepository: WalletRepository,
    private val userPreference: UserPreference
) {
    operator fun invoke(
    ): Flow<ResultState<WalletResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = walletRepository.getWallet()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                userPreference.logout()
            }
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, WalletResponse::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}