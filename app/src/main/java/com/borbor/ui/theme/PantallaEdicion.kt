package com.borbor.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.borbor.viewmodel.StockViewModel

@Composable
fun PantallaEdicion(navController: NavController, vm: StockViewModel, id: Int) {
    val producto = vm.obtenerProducto(id) ?: return
    var stock by remember { mutableStateOf(producto.stockActual) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(producto.nombre, style = MaterialTheme.typography.headlineMedium)
        Text(producto.descripcion)
        Text("Stock: $stock", fontSize = 30.sp)

        Row {
            Button(onClick = { stock++ }) { Text("+1") }
            Button(onClick = { if (stock > 0) stock-- }, enabled = stock > 0) { Text("-1") }
        }

        Button(onClick = {
            vm.actualizarStock(id, stock)
            navController.popBackStack()
        }) {
            Text("Guardar y Volver")
        }
    }
}
