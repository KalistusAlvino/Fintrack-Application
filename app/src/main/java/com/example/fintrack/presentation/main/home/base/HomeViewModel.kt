package com.example.fintrack.presentation.main.home.base

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.auth.LogoutUseCase
import com.example.fintrack.domain.usecase.income.get.ProfileUseCase
import com.example.fintrack.domain.usecase.user.WalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val walletUseCase: WalletUseCase,
    private val profileUseCase: ProfileUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel()
{
    private val _walletState = mutableStateOf(HomeState())
    val walletState: State<HomeState> get() = _walletState

    fun onEvent(event: HomeEvent){
        when(event) {
            HomeEvent.Logout -> {
                logout()
            }
        }
    }
    init {
        getWallet()
        getProfile()
    }

    fun getWallet() {
        walletUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _walletState.value = _walletState.value.copy(isLoading = false, success = false, message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _walletState.value = _walletState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _walletState.value = _walletState.value.copy(
                        wallet = it.data,
                        message = it.message.toString(),
                        success = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProfile() {
        profileUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _walletState.value = _walletState.value.copy(isLoading = false, success = false, message = it.message.toString())

                }
                is ResultState.Loading -> {
                    _walletState.value = _walletState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _walletState.value = _walletState.value.copy(isLoading = false, success = true, message = it.message.toString(), profile = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun logout(){
        logoutUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _walletState.value = _walletState.value.copy(isLoading = false, success = false, message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _walletState.value = _walletState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _walletState.value = _walletState.value.copy(isLoading = false, success = true, message = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}