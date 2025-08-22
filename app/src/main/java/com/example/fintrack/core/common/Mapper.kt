package com.example.fintrack.core.common

import com.example.fintrack.data.remote.dto.auth.LoginResponseDTO
import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.data.remote.dto.auth.RegisterResponseDTO
import com.example.fintrack.data.remote.dto.auth.ResendVerifyResponseDTO
import com.example.fintrack.data.remote.dto.income.get.DataIncomeCategoryDTO
import com.example.fintrack.data.remote.dto.income.get.DataIncomeResponseDTO
import com.example.fintrack.data.remote.dto.income.get.DataMonthlyIncomeDTO
import com.example.fintrack.data.remote.dto.income.get.DataThisMonthIncomeDTO
import com.example.fintrack.data.remote.dto.income.post.DataPostIncomeDTO
import com.example.fintrack.data.remote.dto.income.post.PostIncomeDTO
import com.example.fintrack.data.remote.dto.user.WalletResponseDTO
import com.example.fintrack.di.model.auth.LoginResponse
import com.example.fintrack.di.model.auth.ResendVerifiyResponse
import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.di.model.income.get.IncomeResponse
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.di.model.income.get.ThisMonthIncomeResponse
import com.example.fintrack.di.model.income.post.PostIncomeResponse
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

fun DataIncomeResponseDTO.toIncomeResponse(): IncomeResponse {
    return IncomeResponse(
        id = this.id,
        name = this.name,
        images = this.images,
        description = this.description,
        date = this.date,
        amount = this.amount,
        formattedAmount = this.formattedAmount
    )
}

fun DataThisMonthIncomeDTO.toThisMonthIncomeResponse(): ThisMonthIncomeResponse {
    return ThisMonthIncomeResponse(
        amount = this.amount,
        formattedAmount = this.formattedAmount
    )
}

fun DataMonthlyIncomeDTO.toMonthlyIncomeResponse(): MonthlyIncomeResponse {
    return MonthlyIncomeResponse(
        monthName = this.monthName,
        monthKey = this.monthKey,
        totalIncome = this.totalIncome,
        formattedIncome = this.formattedIncome,
    )
}

fun DataIncomeCategoryDTO.toIncomeCategoryResponse(): IncomeCategoryResponse {
    return IncomeCategoryResponse(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}

fun PostIncomeDTO.toPostIncomeResponse(): PostIncomeResponse {
    return PostIncomeResponse(
        success = this.success ?: false,
        message = this.message.toString(),
        walletId = this.data?.walletId,
        categoryId = this.data?.categoryId,
        description = this.data?.description,
        date = this.data?.date,
        amount = this.data?.amount,
        updatedAt = this.data?.updatedAt,
        createdAt = this.data?.createdAt,
        id = this.data?.id
    )
}