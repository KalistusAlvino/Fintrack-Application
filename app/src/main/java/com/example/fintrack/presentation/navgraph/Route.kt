package com.example.fintrack.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val agruments: List<NamedNavArgument> = emptyList()
) {
    object AppStartNavigation : Route(route = "appStartNavigation")

    object OnBoardingScreen: Route(route = "onBoardingScreen")

    object AppAuthNavigation : Route(route = "appAuthNavigation")

    object AppAuthNavigatorScreen : Route(route = "appAuthNavigatorScreen")

    object LoginScreen: Route(route = "loginScreen")

    object RegisterScreen: Route(route = "registerScreen")

    object SuccessScreen: Route(route = "successScreen")

    object TransactionScreen: Route(route = "transactionScreen")

    object FintrackNavigation : Route(route = "fintrackNavigation")

    object FintrackNavigationScreen : Route(route = "fintrackNavigationScreen")

    object SuccessTransactionNavigationScreen : Route(route = "successTransactionScreen")

    object AllTransactionNavigationScreen : Route(route = "allTransactionScreen")

    object MainScreen: Route(route = "mainScreen")
}