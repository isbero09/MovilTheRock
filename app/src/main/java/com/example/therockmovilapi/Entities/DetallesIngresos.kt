package com.example.therockmovilapi.Entities

import androidx.room.PrimaryKey
import java.net.IDN

data class DetallesIngresos(
    val ingreso: Int,
    val producto_id: Int,
    val cantidad: Int,
)
