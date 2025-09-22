package com.example.fintrack.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.di.model.navigation_bar.NavigationItems
import com.example.fintrack.di.model.navigation_drawer.NavigationDrawerItems
import com.example.fintrack.presentation.main.content.CategoryList
import com.example.fintrack.presentation.main.home.base.HomeEvent
import com.example.fintrack.presentation.main.home.base.HomePage
import com.example.fintrack.presentation.main.home.base.HomeViewModel
import com.example.fintrack.presentation.main.home.transaction.TransactionViewModel
import com.example.fintrack.presentation.main.statistic.StatisticPage
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.component.LogoutAlert
import com.example.fintrack.ui.component.ShimmerEffect
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.BlurDark
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.ui.theme.TextColor
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToTransaction: (GetTransactionCategoryResponse, Int) -> Unit,
    navigateToAllTransaction: (Int) -> Unit,
    onEvent: (HomeEvent) -> Unit
) {
    //ViewModel
    val homeViewModel: HomeViewModel = hiltViewModel()
    val transactionViewModel: TransactionViewModel = hiltViewModel()
    //Result
    val walletResult = homeViewModel.walletState.value
    val incomeCategoryResult = transactionViewModel.transactionState.value.incomeCategory
    val expensesCategoryResult = transactionViewModel.transactionState.value.expensesCategory
    //
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    //HomeTabSegment
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    //Modal
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    //Navigation
    val navigationItems = NavigationItems
    //NavigationDrawer
    val navigationDrawerItems = NavigationDrawerItems
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    //TextSizeToDP
    var showDialog by rememberSaveable { mutableStateOf(false) }
    if (showDialog) {
        LogoutAlert(
            onConfirmation = { onEvent(HomeEvent.Logout) },
            onDismissRequest = {
                showDialog = false
            },
            dialogTitle = "Logout",
            dialogText = "Are you sure want to logout?",
            shouldShowDialog = showDialog
        )
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MainColor
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Logo kiri
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(color = BaseColor, shape = RoundedCornerShape(16.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.fintrack_fordrawer),
                                contentDescription = "Fintrack Logo",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        // Nama dan email
                        if (walletResult.isLoading || walletResult.profile == null) {
                            ShimmerEffect(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .background(
                                        color = Color.LightGray, RoundedCornerShape(
                                            16.dp
                                        )
                                    )
                            )
                        } else {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = walletResult.profile.username,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    color = Color.White
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = "Email",
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = walletResult.profile.email,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = BaseColor)
                    NavigationDrawerItem(
                        modifier = Modifier.padding(vertical = 8.dp),
                        label = {
                            Text(
                                text = "Logout",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Default.Logout,
                                contentDescription = "Logout",
                                tint = BaseColor
                            )
                        },
                        onClick = {
                            showDialog = true
                        }
                    )
                }
            }

        }
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        when (selectedNavigationIndex.intValue) {
                            0 ->
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = if (selectedNavigationIndex.intValue == 0) BaseColor else MainColor.copy(
                                            0.9f
                                        )
                                    )
                                }

                            1 -> Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = {
                                        selectedNavigationIndex.intValue = 0
                                    }
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
                        if (walletResult.isLoading || walletResult.wallet == null) {
                            ShimmerEffect(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .height(16.dp)
                                    .width(100.dp)
                                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                            )
                        } else {
                            Text(
                                text = "Hi, ${walletResult.wallet?.username}!",
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontSize = 16.sp,
                                color = if (selectedNavigationIndex.intValue == 0) BaseColor else TextColor,
                                modifier = Modifier.padding(end = 16.dp)
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
                                    imageVector = item.activeIcon,
                                    contentDescription = item.title,
                                    tint = if (selected) BaseColor else MainColor
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MainColor // hilangin background bulat
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
                    0 -> HomePage(
                        selectedTab = selectedTab,
                        onSelectionChange = { selectedTab = it },
                        allTransaction = { navigateToAllTransaction(it) }
                    )

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
                            text = if (selectedTab == 0) "Add Income transaction" else "Add Expenses transaction",
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
                                when (selectedTab) {
                                    0 -> {
                                        incomeCategoryResult?.forEach { incomeCategory ->
                                            CategoryList(
                                                transactionCategory = incomeCategory,
                                                onClick = {
                                                    navigateToTransaction(
                                                        incomeCategory,
                                                        selectedTab
                                                    )
                                                },
                                            )
                                        }
                                    }

                                    1 -> {
                                        expensesCategoryResult?.forEach { expensesCategory ->
                                            CategoryList(
                                                transactionCategory = expensesCategory,
                                                onClick = {
                                                    navigateToTransaction(
                                                        expensesCategory,
                                                        selectedTab
                                                    )
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
    }

}