package com.example.fintrack.presentation.fintracknavigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.presentation.main.MainScreen
import com.example.fintrack.presentation.main.home.all_transaction.AllTransactionScreen
import com.example.fintrack.presentation.main.home.base.HomeViewModel
import com.example.fintrack.presentation.main.home.success.SuccessTransactionScreen
import com.example.fintrack.presentation.main.home.transaction.TransactionScreen
import com.example.fintrack.presentation.main.home.transaction.TransactionViewModel
import com.example.fintrack.presentation.navgraph.Route

@Composable
fun FintrackNavigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route,
    ) {
        composable(route = Route.MainScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            MainScreen(
                navigateToTransaction = { transactionCategory, selectedTab ->
                    navigateToTransaction(
                        navController = navController,
                        transactionCategory = transactionCategory,
                        selectedTab = selectedTab,
                    )
                },
                navigateToAllTransaction = { selectedTab ->
                    navigateToAllTransaction(
                        navController = navController,
                        selectedTab = selectedTab
                    )
                },
                onEvent = viewModel::onEvent
            )
        }
        composable(route = Route.TransactionScreen.route) {
            val viewModel: TransactionViewModel = hiltViewModel()
            val transactionCategory =
                navController.previousBackStackEntry?.savedStateHandle?.get<GetTransactionCategoryResponse>(
                    "GetTransactionCategoryResponse"
                )
            val selectedTab =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int>("SelectedTab")
            if (selectedTab != null && transactionCategory != null) {
                TransactionScreen(
                    modifier = Modifier,
                    onEvent = viewModel::onEvent,
                    navigateToMain = {
                        navController.navigate(Route.MainScreen.route)
                    },
                    transactionCategory = transactionCategory,
                    selectedTab = selectedTab,
                    navigateToSuccessTransaction = {
                        navController.navigate(Route.SuccessTransactionNavigationScreen.route)
                    }
                )
            }
        }
        composable (route = Route.SuccessTransactionNavigationScreen.route){
            SuccessTransactionScreen(
                navigateToMain = {
                    navController.navigate(Route.MainScreen.route)
                }
            )
        }

        composable (route = Route.AllTransactionNavigationScreen.route) {
            val selectedTab =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int>("SelectedTab")
            if (selectedTab != null)
            AllTransactionScreen(
                navigateToMain = {
                    navController.navigate(Route.MainScreen.route)
                },
                selectedTab = selectedTab,
            )
        }
    }
}


private fun navigateToTransaction(
    navController: NavController,
    transactionCategory: GetTransactionCategoryResponse,
    selectedTab: Int
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "GetTransactionCategoryResponse",
        transactionCategory
    )
    navController.currentBackStackEntry?.savedStateHandle?.set("SelectedTab", selectedTab)
    navController.navigate(
        route = Route.TransactionScreen.route
    )
}

private fun navigateToAllTransaction(
    navController: NavController,
    selectedTab: Int
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("SelectedTab",selectedTab)
    navController.navigate(
        route = Route.AllTransactionNavigationScreen.route
    )
}
