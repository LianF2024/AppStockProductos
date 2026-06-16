package com.borbor.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun PantallaIngreso(navController: NavController) {
    var nombre by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Bienvenido a StockPro", style = MaterialTheme.typography.headlineMedium)
        TextField(value = nombre, onValueChange = { nombre = it }, singleLine = true)
        Button(
            onClick = { navController.navigate("inventario/$nombre") },
            enabled = nombre.length >= 3
        ) {
            Text("Ingresar al Sistema")
        }
    }
}


