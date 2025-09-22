package com.example.fintrack.di.repository.user

import com.example.fintrack.core.common.toWalletResponse
import com.example.fintrack.core.common.toProfileResponse
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.di.model.user.ProfileResponse
import com.example.fintrack.di.model.user.WalletResponse
import com.example.fintrack.domain.di.user.WalletRepository
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(private val fintrackApi: FintrackApi) : WalletRepository {
    override suspend fun getWallet(): WalletResponse {
        return fintrackApi.getWallet().toWalletResponse()
    }

    override suspend fun getProfile(): ProfileResponse {
        return fintrackApi.getProfile().data!!.toProfileResponse()
    }
}

