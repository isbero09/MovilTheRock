package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class listaCategoria : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_categoria, container, false)

        val rv_lista_categorias =view.findViewById<RecyclerView>(R.id.rv_lista_categorias)

        view.findViewById<Button>(R.id.btn_nueva_categoria).setOnClickListener {
            findNavController().navigate(R.id.action_listaCategoria_to_crearCategoria);
        }

        return view
    }
}