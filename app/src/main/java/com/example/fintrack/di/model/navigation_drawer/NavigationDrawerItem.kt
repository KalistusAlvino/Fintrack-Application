package com.example.fintrack.di.model.navigation_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val title: String,
    val name: String,
    val icon: ImageVector,
)

val NavigationDrawerItems = listOf(
    NavigationDrawerItem(
        title = "profile",
        name = "Profile",
        icon = Icons.Default.Person,
    ),
    NavigationDrawerItem(
        title = "logout",
        name = "Logout",
        icon = Icons.AutoMirrored.Filled.Logout,
    ),
)