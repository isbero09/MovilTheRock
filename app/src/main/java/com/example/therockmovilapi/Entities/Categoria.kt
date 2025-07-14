package com.example.therockmovilapi.Entities


data class Categoria (
    val nombre: String,
    val descripcion: String,
    val estado: String // si Laravel te manda "Activo" / "Inactivo"
)