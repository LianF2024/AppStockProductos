package com.borbor.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var stockActual: Int
)

class StockViewModel : ViewModel() {
    // Lista reactiva de productos iniciales
    val listaProductos = mutableStateListOf(
        Producto(1, "Laptop", "Laptop de oficina", 800.0, 10),
        Producto(2, "Mouse", "Mouse inalámbrico", 20.0, 25),
        Producto(3, "Teclado", "Teclado mecánico", 50.0, 15),
        Producto(4, "Monitor", "Monitor 24 pulgadas", 150.0, 3),
        Producto(5, "Impresora", "Impresora multifuncional", 120.0, 0),
        Producto(6, "USB", "Memoria USB 32GB", 10.0, 2)
    )

    fun obtenerProducto(id: Int): Producto? =
        listaProductos.find { it.id == id }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        listaProductos.find { it.id == id }?.stockActual = nuevaCantidad
    }

    fun calcularValorTotalInventario(): Double =
        listaProductos.sumOf { it.precio * it.stockActual }

    fun obtenerProductosEnRiesgo(): List<Producto> =
        listaProductos.filter { it.stockActual < 5 }
}
