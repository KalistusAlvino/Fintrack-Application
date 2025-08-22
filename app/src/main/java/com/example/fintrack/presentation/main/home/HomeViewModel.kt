package com.example.fintrack.presentation.main.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.income.get.IncomeCategoryUseCase
import com.example.fintrack.domain.usecase.income.get.IncomeUseCase
import com.example.fintrack.domain.usecase.income.get.MonthlyIncomeUseCase
import com.example.fintrack.domain.usecase.income.get.ThisMonthIncomeUseCase
import com.example.fintrack.domain.usecase.user.WalletUseCase
import com.example.fintrack.presentation.main.home.Income.IncomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val walletUseCase: WalletUseCase,
    private val incomeUseCase: IncomeUseCase,
    private val thisMonthIncomeUseCase: ThisMonthIncomeUseCase,
    private val monthlyIncomeUseCase: MonthlyIncomeUseCase,
    private val incomeCategoryUseCase: IncomeCategoryUseCase
): ViewModel()
{
    private val _walletState = mutableStateOf(WalletState())
    val walletState: State<WalletState> get() = _walletState

    private val _incomeState = mutableStateOf(IncomeState())
    val incomeState: State<IncomeState> get() = _incomeState

    init {
        getWallet()
        getIncome()
        getThisMonthIncome()
        getMonthlyIncome()
        getIncome()
        getIncomeCategory()
    }

    fun getWallet() {
        walletUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _walletState.value = WalletState(message = it.message.toString())
                    Log.d("Home VM Error", _walletState.value.message.toString())
                }
                is ResultState.Loading -> {
                    _walletState.value = WalletState(isLoading = true)
                }
                is ResultState.Success -> {
                    _walletState.value = WalletState(
                        data = it.data,
                        message = it.message.toString(),
                        success = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIncome() {
        incomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _incomeState.value = _incomeState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _incomeState.value = _incomeState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _incomeState.value = _incomeState.value.copy(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getThisMonthIncome() {
        thisMonthIncomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _incomeState.value = _incomeState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _incomeState.value = _incomeState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _incomeState.value = _incomeState.value.copy(thisMonthIncome = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMonthlyIncome() {
        monthlyIncomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _incomeState.value = _incomeState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _incomeState.value = _incomeState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _incomeState.value = _incomeState.value.copy(monthlyIncome = it.data)
                    Log.d("monthlyIncome", it.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIncomeCategory() {
        incomeCategoryUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _incomeState.value = _incomeState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _incomeState.value = _incomeState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _incomeState.value = _incomeState.value.copy(incomeCategory = it.data)
                    Log.d("incomeCategory", it.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}