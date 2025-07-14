package com.example.therockmovilapi.Entities

data class Producto(
    val nombre: String,
    val precio: Double,      // Usar Double para decimal
    val stock: Int,
    val categoria_id: Int,
    val descripcion: String,
    val estado: String       // "Activo" o "Inactivo"
)