package com.example.therockmovilapi.Entities

import androidx.room.PrimaryKey

data class MenbresiasUsuario(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val usuario: String,          // Puede ser cédula, ID o email según tu lógica
    val membresia: String,        // Puede ser nombre de la membresía
    val membresia_id: Int,
    val precio: Double,
    val fecha_pago: String,
    val fecha_inicio: String,
    val fecha_fin: String,
    val estado: String
)
