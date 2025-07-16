package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Apis.VentaApiService
import kotlinx.coroutines.launch

class listaVenta : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_venta, container, false)

        val rv_lista_ventas = view.findViewById<RecyclerView>(R.id.rv_lista_venta)

        view.findViewById<Button>(R.id.btn_nueva_venta).setOnClickListener {
            findNavController().navigate(R.id.action_listaVenta_to_crearVenta);
        }

        lifecycleScope.launch {
            try {
                val venta = VentaApiService
                    .getApiManager()
                    .getVentas()
                rv_lista_ventas.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_ventas.adapter = lista_venta_adapter(venta)
            }catch (e: Exception) {
                var message="Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Venta")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        return view
    }
}