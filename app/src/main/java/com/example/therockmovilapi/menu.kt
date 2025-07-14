package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class menu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        view.findViewById<Button>(R.id.lista_usuarios_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaUsuario)
        }

        view.findViewById<Button>(R.id.lista_membresias_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaMenbresia)
        }

        view.findViewById<Button>(R.id.lista_membresiasusuario_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaMenbresiaUsuario)
        }

        view.findViewById<Button>(R.id.lista_productos_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaProducto)
        }

        view.findViewById<Button>(R.id.lista_ventas_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaVenta)
        }

        view.findViewById<Button>(R.id.lista_ingresos_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaIngreso)
        }

        view.findViewById<Button>(R.id.lista_detalleingresos_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaDetalleIngreso)
        }

        view.findViewById<Button>(R.id.lista_categoria_btn).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaCategoria)
        }

        return view
    }
}