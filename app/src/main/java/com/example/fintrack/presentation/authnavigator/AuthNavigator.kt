package com.example.fintrack.presentation.authnavigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintrack.presentation.login.LoginPage
import com.example.fintrack.presentation.login.LoginViewModel
import com.example.fintrack.presentation.navgraph.Route
import com.example.fintrack.presentation.register.RegisterPage
import com.example.fintrack.presentation.register.RegisterViewModel
import com.example.fintrack.presentation.success.SuccessScreen
import com.example.fintrack.presentation.success.SuccessViewModel

@Composable
fun AuthNavigator(
    navigateToHome: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.LoginScreen.route,
    ) {
        composable(route = Route.LoginScreen.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginPage(
                onEvent = viewModel::onEvent,
                navigateToRegister = {
                    navController.navigate(Route.RegisterScreen.route)
                },
                navigateToHome = {
                    navigateToHome()
                }
            )
        }
        composable(route = Route.RegisterScreen.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterPage(
                onEvent = viewModel::onEvent,
                navigateToLogin = {
                    navController.navigate(Route.LoginScreen.route)
                },
                navigateToSuccess = { email ->
                    navigateToSuccess(
                        navController = navController,
                        email = email
                    )
                }
            )
        }
        composable(
            route = Route.SuccessScreen.route
        ) {
            val email = navController.previousBackStackEntry?.savedStateHandle?.get<String>("email")
            val viewModel: SuccessViewModel = hiltViewModel()
            SuccessScreen(
                onEvent = viewModel::onEvent,
                email = email.toString(),
                navigateToLogin = { navController.navigate(Route.LoginScreen.route) }
            )
        }
    }
}

private fun navigateToSuccess(navController: NavController, email: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("email", email)
    navController.navigate(route = Route.SuccessScreen.route)
}
