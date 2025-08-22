package com.example.fintrack.presentation.main.home

import com.example.fintrack.di.model.user.WalletResponse

data class WalletState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: WalletResponse? = null,
    var message: String = ""
)
