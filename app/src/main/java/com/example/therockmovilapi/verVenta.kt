package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.VentaApiService
import kotlinx.coroutines.launch

class verVenta : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_venta, container, false)

        view.findViewById<Button>(R.id.btn_ver_venta_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verVenta_to_listaVenta)
        }

        view.findViewById<Button>(R.id.btn_ver_venta_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verVenta_to_editarVenta, bundle)
        }

        lifecycleScope.launch {
            val venta = VentaApiService.getApiManager().getVenta(
                arguments?.getInt("id") ?: 0
            )

            view.findViewById<TextView>(R.id.tv_ver_venta_id)
                .text = "ID: " + venta.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_venta_cliente)
                .text = "Cliente: " + venta.cliente

            view.findViewById<TextView>(R.id.tv_ver_venta_vendedor)
                .text = "Vendedor: " + venta.vendedor

            view.findViewById<TextView>(R.id.tv_ver_venta_producto)
                .text = "Producto: " + venta.producto

            view.findViewById<TextView>(R.id.tv_ver_venta_precio)
                .text = "Precio: $" + venta.precio.toString()

            view.findViewById<TextView>(R.id.tv_ver_venta_fecha)
                .text = "Fecha de Venta: " + venta.fecha_venta

            view.findViewById<TextView>(R.id.tv_ver_venta_pagado)
                .text = "Pagado: " + if (venta.pagado) "Sí" else "No"

            view.findViewById<TextView>(R.id.tv_ver_venta_fecha_pago)
                .text = "Fecha de Pago: " + (venta.fecha_pago ?: "No registrado")
        }

        return view
    }
}