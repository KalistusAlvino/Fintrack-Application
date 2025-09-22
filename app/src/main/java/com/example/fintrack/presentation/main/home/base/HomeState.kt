package com.example.fintrack.presentation.main.home.base

import com.example.fintrack.di.model.user.ProfileResponse
import com.example.fintrack.di.model.user.WalletResponse

data class HomeState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val wallet: WalletResponse? = null,
    val profile: ProfileResponse? = null,
    var message: String = ""
)
