package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.VentaApiService
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

            view.findViewById<CheckBox>(R.id.venta_pagado_editado)
                .isChecked = venta.pagado

            view.findViewById<EditText>(R.id.venta_fecha_pago_editado)
                .setText(venta.fecha_pago ?: "")
        }


        return view
    }
}