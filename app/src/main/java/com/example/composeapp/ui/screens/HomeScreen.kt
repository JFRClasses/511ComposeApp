package com.example.composeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", )
        Button(onClick = {
           navController.navigate("settings/40")
        }) {
            Text(text = "Navegar a settings")
        }
        Button(onClick = { navController.navigate("calendar") }) {
            Text(text = "Navegar a calendar")
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController)
}