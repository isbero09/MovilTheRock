package com.example.therockmovilapi.Entities

data class Usuario(
    val cedula: String,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val telefono: String,
    val estado: String,
    val fecha_nacimiento: String
)