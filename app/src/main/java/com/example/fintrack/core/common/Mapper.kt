package com.example.fintrack.core.common

import com.example.fintrack.data.remote.dto.auth.LoginResponseDTO
import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.data.remote.dto.auth.RegisterResponseDTO
import com.example.fintrack.data.remote.dto.auth.ResendVerifyResponseDTO
import com.example.fintrack.data.remote.dto.transaction.GetMonthlySummaryDTO
import com.example.fintrack.data.remote.dto.transaction.GetThisMonthSummaryDTO
import com.example.fintrack.data.remote.dto.transaction.GetTransactionCategoryDTO
import com.example.fintrack.data.remote.dto.transaction.GetTransactionDTO
import com.example.fintrack.data.remote.dto.transaction.PostTransactionDTO
import com.example.fintrack.data.remote.dto.user.WalletResponseDTO
import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.di.model.Transaction.GetThisMonthSummaryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.di.model.Transaction.PostTransactionResponse
import com.example.fintrack.di.model.auth.LoginResponse
import com.example.fintrack.di.model.auth.ResendVerifiyResponse
import com.example.fintrack.di.model.user.WalletResponse

fun RegisterResponseDTO.toRegisterResponse(): RegisterResponse {
    return RegisterResponse(
        success = this.success ?: false,
        message = this.message.toString(),
        email = this.data?.email
    )
}

fun ResendVerifyResponseDTO.toResendVerifyResponse(): ResendVerifiyResponse {
    return ResendVerifiyResponse(
        success = this.success ?: false,
        message = this.message.toString(),
        email = this.data?.email
    )
}

fun LoginResponseDTO.toLoginResponse(): LoginResponse {
    return LoginResponse(
        success = this.success ?: false,
        message = this.message.toString(),
        id = this.data?.id,
        username = this.data?.username,
        token = this.data?.token
    )
}

fun WalletResponseDTO.toWalletResponse(): WalletResponse {
    return WalletResponse(
        success = this.success ?: false,
        message = this.message.toString(),
        id = this.data?.id,
        username = this.data?.username,
        balance = this.data?.balance
    )
}

fun GetThisMonthSummaryDTO.toThisMonthIncomeResponse(): GetThisMonthSummaryResponse {
    return GetThisMonthSummaryResponse(
        amount = this.amount,
        formattedAmount = this.formattedAmount
    )
}

fun GetMonthlySummaryDTO.toMonthlySummaryResponse(): GetMonthlySummaryResponse {
    return GetMonthlySummaryResponse(
        monthName = this.monthName,
        monthKey = this.monthKey,
        total = this.total,
        formattedTotal = this.formattedTotal,
    )
}

fun PostTransactionDTO.toPostTransactionResponse(): PostTransactionResponse {
    return PostTransactionResponse(
        walletId = this.walletId,
        categoryId = this.categoryId,
        description = this.description,
        date = this.date,
        amount = this.amount,
        updatedAt = this.updatedAt,
        createdAt = this.createdAt,
        id = this.id
    )
}

fun GetTransactionDTO.toTransactionResponse(): GetTransactionResponse {
    return GetTransactionResponse(
        id = this.id,
        name = this.name,
        images = this.images,
        description = this.description,
        date = this.date,
        amount = this.amount,
        formattedAmount = this.formattedAmount
    )
}

fun GetTransactionCategoryDTO.toTransactionCategoryResponse(): GetTransactionCategoryResponse {
    return GetTransactionCategoryResponse(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}