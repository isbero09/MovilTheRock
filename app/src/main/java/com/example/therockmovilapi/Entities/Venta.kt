package com.example.therockmovilapi.Entities

data class Venta(
    val id: Int,
    val cliente: String,
    val vendedor: String,
    val producto: String,
    val precio: Double,
    val fecha_venta: String,   // formato "YYYY-MM-DD" o "YYYY-MM-DD HH:mm:ss"
    val pagado: String,       // si Laravel usa boolean, si es string cambia a String
    val fecha_pago: String?    // nullable, puede ser null si no hay pago aún
)