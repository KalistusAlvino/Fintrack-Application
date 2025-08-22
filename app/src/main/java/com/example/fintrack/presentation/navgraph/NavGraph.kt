package com.example.fintrack.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.compose.rememberNavController
import com.example.fintrack.presentation.authnavigator.AuthNavigator
import com.example.fintrack.presentation.fintracknavigator.FintrackNavigator
import com.example.fintrack.presentation.onboarding.OnBoardingPage
import com.example.fintrack.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingPage(onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.AppAuthNavigation.route,
            startDestination = Route.AppAuthNavigatorScreen.route
        ) {
            composable(route = Route.AppAuthNavigatorScreen.route){
                AuthNavigator(
                    navigateToHome = {
                        navController.navigate(Route.FintrackNavigation.route) {
                            // Clear auth navigation from back stack
                            popUpTo(Route.AppAuthNavigation.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        navigation(
            route = Route.FintrackNavigation.route,
            startDestination = Route.FintrackNavigationScreen.route
        ) {
            composable(route = Route.FintrackNavigationScreen.route){
                FintrackNavigator()
            }
        }
    }
}