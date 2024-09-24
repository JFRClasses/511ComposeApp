package com.example.composeapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.composeapp.models.BottomNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController){
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar {
        BottomNavigationItem.items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(bottomNavigationItem.route)
                },
                label = {
                    Text(bottomNavigationItem.title)
                },
                icon = {
                    Icon(
                        imageVector = bottomNavigationItem.icon,
                        contentDescription = bottomNavigationItem.title
                    )
                })
        }
    }
}