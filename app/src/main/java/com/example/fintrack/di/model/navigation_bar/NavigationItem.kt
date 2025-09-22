package com.example.fintrack.di.model.navigation_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fintrack.R

data class NavigationItem(
    val title: String,
    val activeIcon: ImageVector,
)

val NavigationItems = listOf(
    NavigationItem(
        title = "Home",
        activeIcon = Icons.Default.Home,
    ),
    NavigationItem(
        title = "Statistic",
        activeIcon = Icons.Default.PieChart,
    )
)
