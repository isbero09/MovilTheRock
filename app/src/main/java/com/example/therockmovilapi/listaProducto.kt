package com.example.therockmovilapi

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Apis.ProductoApiService
import kotlinx.coroutines.launch

class listaProducto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_producto, container, false)

        val rv_lista_producto = view.findViewById<RecyclerView>(R.id.rv_lista_producto)

        view.findViewById<Button>(R.id.btn_nuevo_producto).setOnClickListener {
            findNavController().navigate(R.id.action_listaProducto_to_crearProducto);
        }

        lifecycleScope.launch {
            try {
                val productos = ProductoApiService
                    .getApiManager()
                    .getProducto()
                rv_lista_producto.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_producto.adapter = lista_producto_adapter(productos)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Producto")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }
}