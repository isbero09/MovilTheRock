package com.example.therockmovilapi.Entities

import androidx.room.PrimaryKey

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
