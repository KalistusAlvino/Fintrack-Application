package com.example.fintrack.presentation.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.R
import com.example.fintrack.presentation.main.content.MoneyChart
import com.example.fintrack.presentation.main.content.SegmentedButton
import com.example.fintrack.presentation.main.content.TransactionList
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.TextColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    selectedTab: Int,
    onSelectionChange: (Int) -> Unit,
) {
    //ViewModel
    val viewModel: HomeViewModel = hiltViewModel()
    //Result
    val walletResult = viewModel.walletState.value
    val incomeResult = viewModel.incomeState.value
    //SegmentedButton
    val tabs = listOf("Income", "Expense")
    //Chart
    val incomeChartColor = listOf(MainColor, BaseColor)
    val expensesChartColor = listOf(AlertColor, BaseColor)
    //TopSection
    val gradientColors = listOf(Color(0xFF296E6F), MainColor)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseColor.copy(0.9f))
    ) {
        val screenHeight = maxHeight
        val topSectionHeight = screenHeight * 0.33f // 1/3 dari tinggi layar
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Bagian atas dengan gradient hijau
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topSectionHeight)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = gradientColors,
                                    start = Offset(0f, Float.POSITIVE_INFINITY),
                                    end = Offset(0f, 0f)
                                ),
                                RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            ),
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 42.5.dp, end = 42.5.dp)
                                .padding(bottom = 50.dp), // Beri ruang untuk segmented button
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "IDR ${walletResult.data?.balance}",
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.inter_extrabold_24pt)),
                                color = BaseColor
                            )
                            Text(
                                text = "Your total balance",
                                fontSize = 12.sp,
                                color = BaseColor,
                                fontFamily = FontFamily(Font(R.font.inter_medium_24pt)),
                            )
                        }
                    }
                }
            }
            item {
                // Segmented Button di posisi yang dihitung secara dinamis
                SegmentedButton(
                    selectedIndex = selectedTab,
                    cornerRadius = 15.dp,
                    items = tabs,
                    onSelectionChange = { index ->
                        onSelectionChange(index)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                )
            }
            item {
                // Konten berdasarkan tab yang dipilih
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = BaseColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                Text(
                                    text = "This month income",
                                    color = TextColor,
                                    fontSize = 8.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                                    lineHeight = 10.sp
                                )
                                Text(
                                    text = "IDR ${incomeResult.thisMonthIncome?.formattedAmount}",
                                    color = TextColor,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    modifier = Modifier.padding(start = 20.dp),
                                    lineHeight = 21.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                // Placeholder untuk chart
                                incomeResult.monthlyIncome?.let { data ->
                                    MoneyChart(
                                        monthlyIncome = data,
                                        chartColor = if (selectedTab == 0) incomeChartColor else expensesChartColor
                                    )
                                }
                            }
                        }
                        Text(
                            text = "You recent income",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 16.dp),
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 16.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = BaseColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        ) {
                            incomeResult.data?.let {
                                Column(
                                    modifier = Modifier.fillMaxWidth()

                                ) {
                                    if (it.isEmpty()) {
                                        Text(
                                            text = "Start adding your income to track it here",
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            fontSize = 10.sp,
                                            fontStyle = FontStyle.Italic,
                                            color = Color.Gray,
                                            modifier = Modifier.padding(16.dp),
                                        )
                                    }
                                    it.forEach { income ->
                                        TransactionList(
                                            income = income,
                                            boxIconColor = if (selectedTab == 0) MainColor else AlertColor,
                                            icon = if (selectedTab == 0) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward
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
}


//@Preview(
//    widthDp = 360,
//    heightDp = 720
//)
//@Composable
//private fun HomePagePreview() {
//    FintrackTheme {
//        HomePage()
//    }
//}