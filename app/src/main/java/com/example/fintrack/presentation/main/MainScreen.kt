package com.example.fintrack.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.R
import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.di.model.navigation_bar.NavigationItems
import com.example.fintrack.presentation.main.content.CategoryList
import com.example.fintrack.presentation.main.home.HomePage
import com.example.fintrack.presentation.main.home.HomeViewModel
import com.example.fintrack.presentation.main.statistic.StatisticPage
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToTransaction: (IncomeCategoryResponse) -> Unit
) {
    //ViewModel
    val viewModel: HomeViewModel = hiltViewModel()
    //Result
    val walletResult = viewModel.walletState.value
    val incomeCategoryResult = viewModel.incomeState.value
    //
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    //HomeTabSegment
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    //SegmentedButton
    val tabs = listOf("Income", "Expense")
    var selectedSegmented by rememberSaveable { mutableIntStateOf(0) }
    //Modal
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    //Navigation
    val navigationItems = NavigationItems
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    when (selectedNavigationIndex.intValue) {
                        0 -> Text(
                            text = "Hi, ${walletResult.data?.username}!",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 16.sp,
                            color = BaseColor
                        )

                        1 -> Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    Icons.Default.ArrowBackIosNew,
                                    contentDescription = "Back Home"
                                )
                            }
                            Text(
                                text = "Statistic",
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontSize = 16.sp,
                                color = TextColor
                            )
                        }
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = if (selectedNavigationIndex.intValue == 0) MainColor else BaseColor.copy(
                        0.9f
                    )
                ),
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = if (selectedNavigationIndex.intValue == 0) BaseColor else MainColor.copy(
                                0.9f
                            )
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = BaseColor,
            ) {
                navigationItems.forEachIndexed { index, item ->
                    val selected = selectedNavigationIndex.intValue == index
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            selectedNavigationIndex.intValue = index
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(
                                    id = if (selected) item.activeIcon else item.inactiveIcon,
                                ),
                                contentDescription = item.title, tint = MainColor
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent // hilangin background bulat
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            if (selectedNavigationIndex.intValue == 0) {
                SmallFloatingActionButton(
                    onClick = {
                        showBottomSheet = true
                    },
                    shape = CircleShape,
                    containerColor = if (selectedTab == 0) MainColor else AlertColor,
                    contentColor = BaseColor,
                ) {
                    Icon(
                        Icons.Filled.Add, "Add", modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { contentPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            when (selectedNavigationIndex.intValue) {
                0 -> HomePage(selectedTab = selectedTab, onSelectionChange = { selectedTab = it })
                1 -> StatisticPage()
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Add transaction",
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            incomeCategoryResult.incomeCategory?.let {
                                it.forEach { incomeCategory ->
                                    CategoryList(
                                        incomeCategory = incomeCategory,
                                        onClick = navigateToTransaction
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