package com.example.fintrack.domain.di.user

import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.di.model.user.ProfileResponse
import com.example.fintrack.di.model.user.WalletResponse

interface WalletRepository {
    suspend fun getWallet() : WalletResponse

    suspend fun getProfile() : ProfileResponse
}