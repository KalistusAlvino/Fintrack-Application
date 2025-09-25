package com.example.fintrack.presentation.main.home.all_transaction

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fintrack.R
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.presentation.main.content.TransactionList
import com.example.fintrack.presentation.main.home.transaction.TransactionEvent
import com.example.fintrack.presentation.main.home.transaction.TransactionViewModel
import com.example.fintrack.ui.component.ErrorPagingItem
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.component.ShimmerEffect
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.BlurDark
import com.example.fintrack.ui.theme.FintrackTheme
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.ui.theme.TextColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTransactionScreen(
    navigateToMain: () -> Unit,
    selectedTab: Int,
) {
    val transactionViewModel: TransactionViewModel = hiltViewModel()
    val incomesPagingItems: LazyPagingItems<GetTransactionResponse> =
        transactionViewModel.allIncomesState.collectAsLazyPagingItems()
    val expensesPagingItems: LazyPagingItems<GetTransactionResponse> =
        transactionViewModel.allExpensesState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navigateToMain()
                            },
                            modifier = Modifier
                                .border(
                                    width = 0.5.dp,
                                    shape = CircleShape,
                                    color = Color.Gray.copy(0.6f)
                                )
                                .size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back Home",
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = if (selectedTab == 0) "Income Transaction" else "Expenses Transaction",
                            fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                            fontSize = 16.sp,
                            color = TextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BaseColor.copy(0.9f)
                ),
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            when (selectedTab) {
                0 -> {
                    items(incomesPagingItems.itemCount) { index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = BaseColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        ) {
                            TransactionList(
                                boxIconColor = MainColor,
                                icon = Icons.Default.ArrowDownward,
                                selectedTab = selectedTab,
                                transaction = incomesPagingItems[index]!!
                            )
                        }
                    }
                    incomesPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                items(10) {
                                    ShimmerEffect(
                                        modifier = Modifier.fillMaxWidth()
                                            .height(95.dp)
                                            .padding(top = 16.dp, bottom = 16.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    )
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorPagingItem(
                                        message = "Failed to load incomes",
                                        onRetry = {
                                            incomesPagingItems.retry()
                                        }
                                    )
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                items(10) {
                                    ShimmerEffect(
                                        modifier = Modifier.fillMaxWidth()
                                            .height(95.dp)
                                            .padding(top = 16.dp, bottom = 16.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    )
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorPagingItem(
                                        message = "Failed to load incomes",
                                        onRetry = {
                                            incomesPagingItems.retry()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                1 -> {
                    items(expensesPagingItems.itemCount) { index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = BaseColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        ) {
                            TransactionList(
                                boxIconColor = AlertColor,
                                icon = Icons.Default.ArrowDownward,
                                selectedTab = selectedTab,
                                transaction = expensesPagingItems[index]!!
                            )
                        }
                    }
                    expensesPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                items(3) {
                                    ShimmerEffect(
                                        modifier = Modifier.fillMaxWidth()
                                            .height(95.dp)
                                            .padding(top = 16.dp, bottom = 16.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    )
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorPagingItem(
                                        message = "Failed to load expenses",
                                        onRetry = {
                                            expensesPagingItems.retry()
                                        }
                                    )
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                items(10) {
                                    ShimmerEffect(
                                        modifier = Modifier.fillMaxWidth()
                                            .height(95.dp)
                                            .padding(top = 16.dp, bottom = 16.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    )
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorPagingItem(
                                        message = "Failed to load incomes",
                                        onRetry = {
                                            expensesPagingItems.retry()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun AllTransactionPreview() {
//    FintrackTheme {
//        AllTransactionScreen(
//            navigateToMain = { },
//            selectedTab = 0
//        )
//    }
//}