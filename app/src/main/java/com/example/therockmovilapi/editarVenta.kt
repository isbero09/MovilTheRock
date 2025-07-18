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
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.VentaApiService
import com.example.therockmovilapi.Entities.Venta
import kotlinx.coroutines.launch

class editarVenta : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_venta, container, false)

        lifecycleScope.launch{
            val venta = VentaApiService.getApiManager()
                .getVenta(arguments?.getInt("id") ?: 0)

            view.findViewById<EditText>(R.id.venta_cliente_editado)
                .setText(venta.cliente)

            view.findViewById<EditText>(R.id.venta_vendedor_editado)
                .setText(venta.vendedor)

            view.findViewById<EditText>(R.id.venta_producto_editado)
                .setText(venta.producto)

            view.findViewById<EditText>(R.id.venta_precio_editado)
                .setText(venta.precio.toString())

            view.findViewById<EditText>(R.id.venta_fechaventa_editado)
                .setText(venta.fecha_venta)

            view.findViewById<EditText>(R.id.venta_pagado_editado)
                .setText(venta.pagado)

            view.findViewById<EditText>(R.id.venta_fecha_pago_editado)
                .setText(venta.fecha_pago ?: "")
        }

        view.findViewById<Button>(R.id.btn_update_venta).setOnClickListener {
            val cliente = view.findViewById<EditText>(R.id.venta_cliente_editado)
                .text.toString().trim()

            val vendedor = view.findViewById<EditText>(R.id.venta_vendedor_editado)
                .text.toString().trim()

            val producto = view.findViewById<EditText>(R.id.venta_producto_editado)
                .text.toString().trim()

            val precio = view.findViewById<EditText>(R.id.venta_precio_editado)
                .text.toString().trim().toDoubleOrNull() ?: 0.0

            val fechaVenta = view.findViewById<EditText>(R.id.venta_fechaventa_editado)
                .text.toString().trim()

            val pagado = view.findViewById<EditText>(R.id.venta_pagado_editado)
                .text.toString().trim()

            val fechaPago = view.findViewById<EditText>(R.id.venta_fecha_pago_editado)
                .text.toString().trim().ifEmpty { null }

            val idVenta = arguments?.getInt("id") ?: 0

            lifecycleScope.launch {
                try {
                    VentaApiService.getApiManager()
                        .putVenta(
                            Venta( idVenta, cliente, vendedor, producto,
                            precio, fechaVenta, pagado, fechaPago
                        ),
                        idVenta
                    )

                    Toast.makeText(
                        context, "Venta actualizada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarVenta_to_verVenta, Bundle().apply {
                        putInt("id", idVenta)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar venta: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
}