package com.borbor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.borbor.ui.theme.*
import com.borbor.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = StockViewModel()

        setContent {
            MaterialTheme {
                Surface {
                    AppNavigation(vm)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(vm: StockViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ingreso") {
        composable("ingreso") { PantallaIngreso(navController) }
        composable(
            route = "inventario/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            PantallaInventario(navController, vm, nombre)
        }
        composable(
            route = "edicion/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            PantallaEdicion(navController, vm, id)
        }
        composable("reporte") { PantallaReporte(navController, vm) }
    }
}

