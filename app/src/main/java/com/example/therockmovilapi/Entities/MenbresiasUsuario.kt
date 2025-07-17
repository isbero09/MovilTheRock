package com.example.therockmovilapi.Entities

data class MenbresiasUsuario(
    val id: Int,
    val usuario: String,
    val membresia: String,
    val membresia_id: Int,
    val precio: Double,
    val fecha_pago: String,
    val fecha_inicio: String,
    val fecha_fin: String,
    val estado: String
)
