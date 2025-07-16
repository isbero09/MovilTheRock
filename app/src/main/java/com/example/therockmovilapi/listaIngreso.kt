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
import com.example.therockmovilapi.Apis.IngresoApiService
import kotlinx.coroutines.launch

class listaIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_ingreso, container, false)

        val rv_lista_ingreso = view.findViewById<RecyclerView>(R.id.rv_lista_ingreso)

        view.findViewById<Button>(R.id.btn_nuevo_ingreso).setOnClickListener {
            findNavController().navigate(R.id.action_listaIngreso_to_crearIngreso);
        }

        lifecycleScope.launch {
            try {
                val ingresos = IngresoApiService
                    .getApiManager()
                    .getIngreso()
                rv_lista_ingreso.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_ingreso.adapter = lista_ingreso_adapter (ingresos)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Ingreso")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }
}