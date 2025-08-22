package com.example.fintrack.di.repository.auth

import com.example.fintrack.core.common.toResendVerifyResponse
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.model.auth.ResendVerifiyResponse
import com.example.fintrack.domain.di.auth.ResendVerifyRepository
import javax.inject.Inject

class ResendVerifyRepositoryImpl @Inject constructor(
    private val fintrackApi: FintrackApi
): ResendVerifyRepository  {

    override suspend fun resendVerify(email: String): ResendVerifiyResponse {
       return fintrackApi.resendVerification(email).toResendVerifyResponse()
    }

}