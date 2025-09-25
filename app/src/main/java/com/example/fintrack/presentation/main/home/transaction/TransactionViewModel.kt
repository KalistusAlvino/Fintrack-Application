package com.example.fintrack.presentation.main.home.transaction


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.domain.usecase.expenses.get.AllExpensesUseCase
import com.example.fintrack.domain.usecase.expenses.get.ExpensesCategoryUseCase
import com.example.fintrack.domain.usecase.expenses.get.ExpensesUseCase
import com.example.fintrack.domain.usecase.expenses.get.MonthlyExpensesUseCase
import com.example.fintrack.domain.usecase.expenses.get.ThisMonthExpensesUseCase
import com.example.fintrack.domain.usecase.income.get.AllIncomeUseCase
import com.example.fintrack.domain.usecase.income.get.IncomeCategoryUseCase
import com.example.fintrack.domain.usecase.income.get.IncomeUseCase
import com.example.fintrack.domain.usecase.income.get.MonthlyIncomeUseCase
import com.example.fintrack.domain.usecase.income.get.ThisMonthIncomeUseCase
import com.example.fintrack.domain.usecase.income.post.PostExpensesUseCase
import com.example.fintrack.domain.usecase.income.post.PostIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
    private val monthlyExpensesUseCase: MonthlyExpensesUseCase,
    private val allIncomeUseCase: AllIncomeUseCase,
    private val allExpensesUseCase: AllExpensesUseCase
) : ViewModel() {
    private val _transactionState = mutableStateOf(TransactionState())
    val transactionState: State<TransactionState> get() = _transactionState

    private val _allIncomesState :MutableStateFlow<PagingData<GetTransactionResponse>> =
            MutableStateFlow(value = PagingData.empty())
    val allIncomesState: MutableStateFlow<PagingData<GetTransactionResponse>> get() = _allIncomesState

    private val _allExpensesState :MutableStateFlow<PagingData<GetTransactionResponse>> =
            MutableStateFlow(value = PagingData.empty())
    val allExpensesState: MutableStateFlow<PagingData<GetTransactionResponse>> get() = _allExpensesState

    private var lastEvent: TransactionEvent? = null

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.PostIncome -> {
                postIncome(event.categoryId, event.amount, event.description)
            }

            is TransactionEvent.PostExpenses -> {
                postExpenses(event.categoryId, event.amount, event.description)
            }
            is TransactionEvent.AllExpenses -> {
                lastEvent = event
                getAllIncomes()
            }
            is TransactionEvent.AllIncomes -> {
                lastEvent = event
                getAllExpenses()
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
        onEvent(TransactionEvent.AllIncomes)
        onEvent(TransactionEvent.AllExpenses)
    }

    fun getIncome() {
        incomeUseCase.invoke().onEach {
            when (it) {
                is ResultState.Error -> {
                    _transactionState.value =
                        _transactionState.value.copy(message = it.message.toString())
                }

                is ResultState.Loading -> {
                    _transactionState.value = _transactionState.value.copy(isLoading = true)
                }

                is ResultState.Success -> {
                    _transactionState.value = _transactionState.value.copy(
                        income = it.data,
                        success = true,
                        isLoading = false
                    )
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
                    _transactionState.value = _transactionState.value.copy(
                        expenses = it.data,
                        success = true,
                        isLoading = false
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    fun getThisMonthIncome() {
        monthIncomeUseCase.invoke().onEach {
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
                    _transactionState.value = _transactionState.value.copy(
                        thisMonthIncome = it.data,
                        success = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getThisMonthExpenses() {
        mothExpensesUseCase.invoke().onEach {
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
                    _transactionState.value = _transactionState.value.copy(
                        thisMonthExpenses = it.data,
                        success = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMonthlyIncome() {
        monthlyIncomeUseCase.invoke().onEach {
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
                    _transactionState.value = _transactionState.value.copy(
                        monthlyIncome = it.data,
                        success = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMonthlyExpenses() {
        monthlyExpensesUseCase.invoke().onEach {
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
                    _transactionState.value = _transactionState.value.copy(
                        monthlyExpenses = it.data,
                        success = true,
                        isLoading = false
                    )
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
                        _transactionState.value.copy(
                            expensesCategory = it.data,
                            success = true,
                            isLoading = false
                        )
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
                    _transactionState.value = _transactionState.value.copy(
                        incomeCategory = it.data,
                        success = true,
                        isLoading = false
                    )
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
                    _transactionState.value = _transactionState.value.copy(
                        postIncome = it.data,
                        success = true,
                        isLoading = false
                    )
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
                    _transactionState.value = _transactionState.value.copy(
                        postExpenses = it.data,
                        success = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllIncomes() {
        viewModelScope.launch {
            allIncomeUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _allIncomesState.value = it
                }
        }
    }
    private fun getAllExpenses() {
        viewModelScope.launch {
            allExpensesUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _allExpensesState.value = it
                }
        }
    }

}