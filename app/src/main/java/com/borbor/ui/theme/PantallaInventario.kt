package com.borbor.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.borbor.viewmodel.StockViewModel

@Composable
fun PantallaInventario(navController: NavController, vm: StockViewModel, nombre: String) {
    var mostrarCriticos by remember { mutableStateOf(false) }
    val productos = if (mostrarCriticos) vm.obtenerProductosEnRiesgo() else vm.listaProductos

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Operario: $nombre", style = MaterialTheme.typography.headlineMedium)

        // 🔎 Fila de botones: Ver Todo, Stock Crítico y Reporte
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Button(onClick = { mostrarCriticos = false }) { Text("Ver Todo") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { mostrarCriticos = true }) { Text("Stock Crítico") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate("reporte") }) { Text("Reporte") }
        }

        LazyColumn {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate("edicion/${producto.id}") }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Precio: $${producto.precio}")
                        Text(
                            "Stock: ${producto.stockActual}",
                            color = if (producto.stockActual < 5) Color.Red else Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}

