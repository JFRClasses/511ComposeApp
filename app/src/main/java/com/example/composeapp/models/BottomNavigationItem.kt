package com.example.composeapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    companion object {
        val items = listOf(
            BottomNavigationItem(route = "home", title = "Home", icon = Icons.Default.Home),
            BottomNavigationItem(
                route = "calendar",
                title = "Calendar",
                icon = Icons.Default.DateRange
            ),
            BottomNavigationItem(
                route = "settings",
                title = "Settings",
                icon = Icons.Default.Settings
            )
        )
    }
}
