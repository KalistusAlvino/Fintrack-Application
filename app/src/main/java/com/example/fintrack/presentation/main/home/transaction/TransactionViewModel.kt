package com.example.fintrack.presentation.main.home.transaction


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.expenses.get.ExpensesCategoryUseCase
import com.example.fintrack.domain.usecase.expenses.get.ExpensesUseCase
import com.example.fintrack.domain.usecase.expenses.get.MonthlyExpensesUseCase
import com.example.fintrack.domain.usecase.expenses.get.ThisMonthExpensesUseCase
import com.example.fintrack.domain.usecase.income.get.IncomeCategoryUseCase
import com.example.fintrack.domain.usecase.income.get.IncomeUseCase
import com.example.fintrack.domain.usecase.income.get.MonthlyIncomeUseCase
import com.example.fintrack.domain.usecase.income.get.ThisMonthIncomeUseCase
import com.example.fintrack.domain.usecase.income.post.PostExpensesUseCase
import com.example.fintrack.domain.usecase.income.post.PostIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val postIncomeUseCase: PostIncomeUseCase,
    private val postExpensesUseCase: PostExpensesUseCase,
    private val expensesUseCase: ExpensesUseCase,
    private val incomeUseCase: IncomeUseCase,
    private val expensesCategoryUseCase: ExpensesCategoryUseCase,
    private val incomeCategoryUseCase: IncomeCategoryUseCase,
    private val monthIncomeUseCase: ThisMonthIncomeUseCase,
    private val mothExpensesUseCase: ThisMonthExpensesUseCase,
    private val monthlyIncomeUseCase: MonthlyIncomeUseCase,
    private val monthlyExpensesUseCase: MonthlyExpensesUseCase
) : ViewModel() {
    private val _transactionState = mutableStateOf(TransactionState())
    val transactionState: State<TransactionState> get() = _transactionState

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.PostIncome -> {
                postIncome(event.categoryId, event.amount, event.description)
            }
            is TransactionEvent.PostExpenses -> {
                postExpenses(event.categoryId, event.amount, event.description)
            }
        }
    }

    init {
        getExpenses()
        getExpensesCategory()
        getIncomeCategory()
        getIncome()
        getThisMonthIncome()
        getThisMonthExpenses()
        getMonthlyIncome()
        getMonthlyExpenses()
    }
    fun getIncome() {
        incomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _transactionState.value = _transactionState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(income = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getExpenses() {
        expensesUseCase.invoke().onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }

                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }

                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(expenses = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun getThisMonthIncome() {
        monthIncomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(thisMonthIncome = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getThisMonthExpenses() {
        mothExpensesUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(thisMonthExpenses = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getMonthlyIncome() {
        monthlyIncomeUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(monthlyIncome = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMonthlyExpenses() {
        monthlyExpensesUseCase.invoke().onEach {
            when(it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(monthlyExpenses = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun getExpensesCategory() {
        expensesCategoryUseCase.invoke().onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                }

                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }

                is ResultState.Success -> {
                    _transactionState.value =
                        _transactionState.value.copy(expensesCategory = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIncomeCategory() {
        incomeCategoryUseCase.invoke().onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                }

                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }

                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(incomeCategory = it.data)
                    _transactionState.value = _transactionState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun postIncome(
        categoryId: Int,
        amount: Long,
        description: String
    ) {
        postIncomeUseCase.invoke(categoryId, amount, description).onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                }

                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }

                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(postIncome = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun postExpenses(
        categoryId: Int,
        amount: Long,
        description: String
    ) {
        postExpensesUseCase.invoke(categoryId, amount, description).onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                }
                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }
                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(postExpenses = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}