package com.example.composeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.example.composeapp.models.BottomNavigationItem
import com.example.composeapp.ui.components.BottomNavigationBar
import com.example.composeapp.ui.components.MyListItem
import com.example.composeapp.ui.screens.CalendarScreen
import com.example.composeapp.ui.screens.HomeScreen
import com.example.composeapp.ui.screens.SettingsScreen
import com.example.composeapp.ui.theme.ComposeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") {
                            HomeScreen(navController)
                        }
                        composable(
                            route = "settings/{value}",
                            arguments = listOf(
                                navArgument("value") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                    nullable = false
                                }
                            )
                        ) {
                            val value = it.arguments?.getInt("value") ?: 0
                            SettingsScreen(value)
                        }
                        
                        composable(route="settings"){
                            SettingsScreen(value = 0)
                        }
                        
                        composable(route = "calendar") {
                            CalendarScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 15.sp
    )
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Ejemplo 1", modifier = Modifier.background(Color.Green))
        Text(text = "Ejemplo 2", modifier = Modifier.background(Color.Magenta))
        Text(text = "Ejemplo 3", modifier = Modifier.background(Color.Red))
        Text(text = "Ejemplo 4", modifier = Modifier.background(Color.Yellow))

    }
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            "Hola",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            "Ejemplo 2",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            "Ejemplo 3",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.Magenta)
        )
        Text(
            "Ejemplo 10",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.Green)

        )
    }
}

@Composable
fun MyBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Hola a todos")
    }
}

// Listas
@Composable
fun MyList(paddingValues: PaddingValues) {
    val foodList = listOf("Hamburguesa", "Pizza", "Tacos", "Sushi", "Ensalada")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(10.dp)
    ) {
        items(foodList) { product ->
            MyListItem(product = product)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun MyComplexLayout() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .weight(0.333f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.333f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxHeight()
                    .weight(0.5f),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(text = "Hola a todos")
            }
            // Segunda Box
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight()
                    .weight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Text("Hola a todos")
            }
        }
        // Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.333f)
                .background(Color.Magenta)
        ) {

        }
    }
}

@Composable
fun MyAdvancedComplexLayoutWithoutCards() {
    val foodList =
        listOf("Hamburguesa", "Pizza", "Tacos", "Sushi", "Ensalada", "Hot Dogs", "Lasagna")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text("Encabezado", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }


        // Filas con diferentes elementos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Caja 1
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Yellow),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Caja 1", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { /* Acción del botón */ }) {
                        Text("Acción")
                    }
                }
            }

            // Caja 2
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Caja 2", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { /* Acción del botón */ }) {
                        Text("Acción")
                    }
                }
            }
        }

        // Lista de elementos
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Text("Lista de Elementos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            LazyColumn {
                items(foodList) { index ->
                    MyListItem(index)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // Pie de página
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Text("Pie de página", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MyState() {
    // var counter = 0
//    var counter by remember {
//        mutableStateOf(0)
//    }
//    var counter by rememberSaveable {
//        mutableStateOf(0)
//    }
    val counter = rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "El valor del contador es: ${counter.value}")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { counter.value-- }) {
                Text("Decrementar")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { counter.value++ }) {
                Text("Incrementar")
            }
        }
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://static.wikia.nocookie.net/doblaje/images/a/ad/Marvel_Super_Hero_Adventures.png/revision/latest?cb=20240215235222&path-prefix=es"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text("Heroes")

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7A
)
@Composable
fun GreetingPreview() {
    ComposeAppTheme {
        MyCard()
    }
}