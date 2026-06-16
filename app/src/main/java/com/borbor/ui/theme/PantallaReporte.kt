package com.borbor.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.borbor.viewmodel.StockViewModel

@Composable
fun PantallaReporte(navController: NavController, vm: StockViewModel) {
    val capitalTotal = vm.calcularValorTotalInventario()
    val productosAgotados = vm.listaProductos.count { it.stockActual == 0 }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Reporte Financiero", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Capital Invertido Total: $${capitalTotal}", style = MaterialTheme.typography.bodyLarge)
        Text("Productos con stock en cero: $productosAgotados", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al Catálogo")
        }
    }
}
