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
import com.example.therockmovilapi.Apis.DetallesIngresosApiService
import kotlinx.coroutines.launch

class listaDetalleIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_detalle_ingreso, container, false)

        val rv_lista_detalleingreso = view.findViewById<RecyclerView>(R.id.rv_lista_detalleingreso)

        view.findViewById<Button>(R.id.btn_nuevo_detalleingreso).setOnClickListener {
            findNavController().navigate(R.id.action_listaDetalleIngreso_to_crearDetalleingreso);
        }

        lifecycleScope.launch {
            try {
                val detallesIngreso = DetallesIngresosApiService
                    .getApiManager()
                    .getDetallesIngresos()
                rv_lista_detalleingreso.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_detalleingreso.adapter = lista_detalleingreso_adapter(detallesIngreso)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Detalle Ingreso")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }

}