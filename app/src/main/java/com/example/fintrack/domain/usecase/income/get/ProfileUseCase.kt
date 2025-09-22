package com.example.fintrack.domain.usecase.income.get

import coil.network.HttpException
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.user.ProfileResponse
import com.example.fintrack.domain.di.user.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {
    operator fun invoke(): Flow<ResultState<ProfileResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = walletRepository.getProfile()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            emit(ResultState.Error(message = e.message.toString()))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message.toString()))
        }
    }
}