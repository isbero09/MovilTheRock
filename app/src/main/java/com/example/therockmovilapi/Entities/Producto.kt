package com.example.therockmovilapi.Entities

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria_id: Int,
    val descripcion: String,
    val estado: String       // "Activo" o "Inactivo"
)