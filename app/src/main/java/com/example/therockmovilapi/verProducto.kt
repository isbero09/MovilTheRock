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
import com.example.therockmovilapi.Apis.ProductoApiService
import kotlinx.coroutines.launch

class verProducto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_producto, container, false)

        view.findViewById<Button>(R.id.btn_ver_producto_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verProducto_to_listaProducto)
        }

        view.findViewById<Button>(R.id.btn_ver_producto_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verProducto_to_editarProducto, bundle)
        }

        lifecycleScope.launch {
            val producto = ProductoApiService.getApiManager().getProducto(
                arguments?.getInt("id") ?: 0
            )

            view.findViewById<TextView>(R.id.tv_ver_producto_id)
                .text = "ID: " + producto.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_producto_nombre)
                .text = "Nombre: " + producto.nombre

            view.findViewById<TextView>(R.id.tv_ver_producto_precio)
                .text = "Precio: $" + producto.precio.toString()

            view.findViewById<TextView>(R.id.tv_ver_producto_stock)
                .text = "Stock: " + producto.stock.toString()

            view.findViewById<TextView>(R.id.tv_ver_producto_categoria_id)
                .text = "Categoría ID: " + producto.categoria_id.toString()

            view.findViewById<TextView>(R.id.tv_ver_producto_descripcion)
                .text = "Descripción: " + producto.descripcion

            view.findViewById<TextView>(R.id.tv_ver_producto_estado)
                .text = "Estado: " + producto.estado
        }


        return view
    }
}