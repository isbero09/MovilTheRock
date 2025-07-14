package com.example.therockmovilapi.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "menbresias")
data class Menbresia(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val fecha_creacion: String,
    val estado: String
)
