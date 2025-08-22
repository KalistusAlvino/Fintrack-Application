package com.example.fintrack.di.model.navigation_bar


import com.example.fintrack.R

data class NavigationItem(
    val title: String,
    val activeIcon: Int,
    val inactiveIcon: Int
)

val NavigationItems = listOf(
    NavigationItem(
        title = "Home",
        activeIcon = R.drawable.home_active,
        inactiveIcon = R.drawable.home_inactive
    ),
    NavigationItem(
        title = "Statistic",
        activeIcon = R.drawable.statistic_active,
        inactiveIcon = R.drawable.statistic_inactive
    )
)
