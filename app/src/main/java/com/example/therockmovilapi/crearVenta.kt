package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.VentaApiService
import com.example.therockmovilapi.Entities.Venta
import kotlinx.coroutines.launch

class crearVenta : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_venta, container, false)

        view.findViewById<Button>(R.id.btn_add_venta).setOnClickListener {
            val cliente = view.findViewById<EditText>(R.id.venta_cliente_nuevo)
                .text.toString().trim()

            val vendedor = view.findViewById<EditText>(R.id.venta_vendedor_nuevo)
                .text.toString().trim()

            val producto = view.findViewById<EditText>(R.id.venta_producto_nuevo)
                .text.toString().trim()

            val precioText = view.findViewById<EditText>(R.id.venta_precio_nuevo)
                .text.toString().trim()
            val precio = if (precioText.isNotEmpty()) precioText.toDouble() else 0.0

            val fechaVenta = view.findViewById<EditText>(R.id.venta_fecha_venta_nuevo)
                .text.toString().trim()

            val pagado = view.findViewById<CheckBox>(R.id.venta_pagado_nuevo).isChecked

            val fechaPagoText = view.findViewById<EditText>(R.id.venta_fecha_pago_nuevo)
                .text.toString().trim()
            val fechaPago: String? = if (fechaPagoText.isNotEmpty()) fechaPagoText else null

            lifecycleScope.launch {
                try {
                    VentaApiService.getApiManager().postVenta(
                        Venta( cliente, vendedor, producto, precio, fechaVenta, pagado, fechaPago )
                    )
                    Toast.makeText(
                        context, "Venta registrada exitosamente",
                        Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Error al registrar venta: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        return view
    }
}