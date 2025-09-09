package com.example.fintrack.presentation.main.home.base

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.user.WalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val walletUseCase: WalletUseCase,
): ViewModel()
{
    private val _walletState = mutableStateOf(HomeState())
    val walletState: State<HomeState> get() = _walletState

    init {
        getWallet()
    }

    fun getWallet() {
        walletUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _walletState.value = HomeState(message = it.message.toString())
                    Log.d("Home VM Error", _walletState.value.message.toString())
                }
                is ResultState.Loading -> {
                    _walletState.value = HomeState(isLoading = true)
                }
                is ResultState.Success -> {
                    _walletState.value = HomeState(
                        wallet = it.data,
                        message = it.message.toString(),
                        success = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}