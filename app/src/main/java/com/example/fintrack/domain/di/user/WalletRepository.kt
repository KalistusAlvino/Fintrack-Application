package com.example.fintrack.domain.di.user

import com.example.fintrack.di.model.user.WalletResponse

interface WalletRepository {
    suspend fun getWallet() : WalletResponse
}