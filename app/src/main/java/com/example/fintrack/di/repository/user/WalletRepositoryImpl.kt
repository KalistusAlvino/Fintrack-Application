package com.example.fintrack.di.repository.user

import com.example.fintrack.core.common.toWalletResponse
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.model.user.WalletResponse
import com.example.fintrack.domain.di.user.WalletRepository
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(private val fintrackApi: FintrackApi) : WalletRepository {
    override suspend fun getWallet(): WalletResponse {
        return fintrackApi.getWallet().toWalletResponse()
    }
}