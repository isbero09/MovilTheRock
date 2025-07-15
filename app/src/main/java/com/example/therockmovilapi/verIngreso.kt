package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.IngresoApiService
import kotlinx.coroutines.launch

class verIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_ingreso, container, false)

        view.findViewById<Button>(R.id.btn_ver_ingreso_atras).setOnClickListener{
            findNavController().navigate(R.id.action_verIngreso_to_listaIngreso)
        }

        view.findViewById<Button>(R.id.btn_ver_ingreso_eliminar).setOnClickListener {
            lifecycleScope.launch {
                IngresoApiService.getApiManager()
                    .deleteIngreso(arguments?.getInt("id") ?:0)

                findNavController()
                    .navigate(R.id.action_verIngreso_to_listaIngreso)
            }
        }

        view.findViewById<Button>(R.id.btn_ver_ingreso_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verIngreso_to_editarIngreso, bundle)
        }

        lifecycleScope.launch {
            val ingreso = IngresoApiService.getApiManager().getIngreso(
                    arguments?.getInt("id") ?: 0 )

            view.findViewById<TextView>(R.id.tv_ver_ingreso_id)
                    .text = "ID: " + ingreso.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_ingreso_cedula)
                    .text = "Cédula: " + ingreso.cedula.toString()

            view.findViewById<TextView>(R.id.tv_ver_ingreso_fecha)
                    .text = "Fecha: " + ingreso.fecha.toString()

            view.findViewById<TextView>(R.id.tv_ver_ingreso_detalles)
                    .text = "Detalles: " + ingreso.detalles.toString()
        }
        return view
    }
}