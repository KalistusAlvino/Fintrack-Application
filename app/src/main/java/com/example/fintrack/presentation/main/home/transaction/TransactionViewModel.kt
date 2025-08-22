package com.example.fintrack.presentation.main.home.transaction

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.income.post.PostIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val postIncomeUseCase: PostIncomeUseCase
): ViewModel() {
    private val _transactionState = mutableStateOf(TransactionState())
    val transactionState: State<TransactionState> get() = _transactionState

    fun onEvent(event: TransactionEvent) {
        when(event) {
            is TransactionEvent.postIncome -> {
                postIncome(event.categoryId, event.amount, event.description)
            }
        }
    }

    fun postIncome(
        categoryId: Int,
        amount: Long,
        description: String
    ) {
        postIncomeUseCase.invoke(categoryId, amount, description).onEach {
            when(it){
                is ResultState.Error -> {
                    Log.d("TransactionVM", _transactionState.value.message.toString())
                    _transactionState.value = TransactionState(message = it.message.toString())
                }
                is ResultState.Loading ->{
                    _transactionState.value = TransactionState(isLoading = true)
                }
                is ResultState.Success -> {
                    Log.d("TransactionVM", _transactionState.value.message.toString())
                    _transactionState.value =
                        TransactionState(data = it.data, message = it.message.toString(), success = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}