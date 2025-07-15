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
import com.example.therockmovilapi.Apis.MenbresiaApiService
import kotlinx.coroutines.launch

class verMembresia : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_membresia, container, false)

        view.findViewById<Button>(R.id.btn_ver_membresia_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verMembresia_to_listaMenbresia)
        }

        view.findViewById<Button>(R.id.btn_ver_membresia_eliminar).setOnClickListener {
            lifecycleScope.launch {
                MenbresiaApiService.getApiManager()
                    .deleteMenbresia(arguments?.getInt("id") ?: 0)
                findNavController()
                    .navigate(R.id.action_verMembresia_to_listaMenbresia)
            }
        }


        view.findViewById<Button>(R.id.btn_ver_membresia_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verMembresia_to_editarMenbresia, bundle)
        }

        lifecycleScope.launch {
            val membresia = MenbresiaApiService.getApiManager().getMenbresia(
                arguments?.getInt("id") ?: 0)

            view.findViewById<TextView>(R.id.tv_ver_membresia_id)
                .text = "ID: " + membresia.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_membresia_nombre)
                .text = "Nombre: " + membresia.nombre

            view.findViewById<TextView>(R.id.tv_ver_membresia_precio)
                .text = "Precio: $" + membresia.precio.toString()

            view.findViewById<TextView>(R.id.tv_ver_membresia_descripcion)
                .text = "Descripción: " + membresia.descripcion

            view.findViewById<TextView>(R.id.tv_ver_membresia_fecha_creacion)
                .text = "Fecha de Creación: " + membresia.fecha_creacion

            view.findViewById<TextView>(R.id.tv_ver_membresia_estado)
                .text = "Estado: " + membresia.estado
        }
        return view
    }
}