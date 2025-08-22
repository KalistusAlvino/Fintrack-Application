package com.example.fintrack.presentation.fintracknavigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.presentation.main.MainScreen
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
            MainScreen(
                navigateToTransaction = { incomeCategory ->
                    navigateToTransaction(
                        navController = navController,
                        incomeCategory = incomeCategory
                    )
                }
            )
        }
        composable (route = Route.TransactionScreen.route){
            val viewModel: TransactionViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<IncomeCategoryResponse>("IncomeCategoryResponse")?.let { incomeCategory ->
                TransactionScreen(
                    modifier = Modifier,
                    onEvent = viewModel::onEvent,
                    navigateToMain = {
                        navController.navigate(Route.MainScreen.route)
                    },
                    incomeCategory = incomeCategory
                )
            }
        }
    }
}

private fun navigateToTransaction(navController: NavController, incomeCategory: IncomeCategoryResponse){
    navController.currentBackStackEntry?.savedStateHandle?.set("IncomeCategoryResponse", incomeCategory)
    navController.navigate(
        route = Route.TransactionScreen.route
    )
}