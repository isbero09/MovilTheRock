package com.example.therockmovilapi.Entities

import androidx.room.PrimaryKey
import java.net.IDN

data class DetallesIngresos(
    @PrimaryKey (autoGenerate = true) val idn: Int = 0,
    val ingreso: Int,
    val producto_id: Int,
    val cantidad: Int,
)
