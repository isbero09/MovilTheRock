package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class listaVenta : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_venta, container, false)

        val rv_lista_ventas = view.findViewById<RecyclerView>(R.id.rv_lista_venta)

        view.findViewById<Button>(R.id.btn_nueva_venta).setOnClickListener {
            findNavController().navigate(R.id.action_menu_to_listaVenta)
        }

        return view
    }
}