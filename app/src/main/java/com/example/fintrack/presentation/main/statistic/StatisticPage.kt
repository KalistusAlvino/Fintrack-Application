package com.example.fintrack.presentation.main.statistic

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.R
import com.example.fintrack.presentation.main.content.RecentTransaction
import com.example.fintrack.presentation.main.content.SegmentedButton
import com.example.fintrack.presentation.main.content.StatisticChart
import com.example.fintrack.presentation.main.home.transaction.TransactionViewModel
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.ui.theme.TextColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun StatisticPage(
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Income", "Expense")
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val transactionViewModel: TransactionViewModel = hiltViewModel()

    val monthlyIncome = transactionViewModel.transactionState.value.monthlyIncome
    val monthlyExpenses = transactionViewModel.transactionState.value.monthlyExpenses

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseColor.copy(0.9f))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = BaseColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        Text(
                            text = "Your transaction statistic",
                            color = TextColor,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                            lineHeight = 10.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        StatisticChart(
                            monthlyIncome = monthlyIncome ?: emptyList(),
                            monthlyExpenses = monthlyExpenses ?: emptyList()
                        )
                    }
                }
            }
            item {
                SegmentedButton(
                    selectedIndex = selectedTab,
                    cornerRadius = 15.dp,
                    items = tabs,
                    onSelectionChange = {
                        selectedTab = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
            item {
                Text(
                    text = "Latest 6 Months Transactions",
                    color = TextColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    lineHeight = 10.sp
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = BaseColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        when(selectedTab) {
                            0 -> {
                                monthlyIncome?.reversed()?.forEach { income ->
                                    RecentTransaction(
                                        boxIconColor = MainColor,
                                        icon = Icons.Default.ArrowDownward,
                                        transaction = income
                                    )
                                }
                            }
                            1 -> {
                                monthlyExpenses?.reversed()?.forEach { income ->
                                    RecentTransaction(
                                        boxIconColor = AlertColor,
                                        icon = Icons.Default.ArrowUpward,
                                        transaction = income
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