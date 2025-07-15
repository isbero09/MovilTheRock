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
import com.example.therockmovilapi.Apis.CategoriaApiService
import kotlinx.coroutines.launch

class verCategoria : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_categoria, container, false)

        view.findViewById<Button>(R.id.btn_ver_categoria_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verCategoria_to_listaCategoria)
        }

        view.findViewById<Button>(R.id.btn_ver_categoria_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verCategoria_to_editarCategoria, bundle)
        }

        lifecycleScope.launch{
            var categoria= CategoriaApiService.getApiManager().getCategoria(
                arguments?.getInt(" id")?:0)

            view.findViewById<TextView>(R.id.tv_ver_categoria_id)
                .text="ID:"+ categoria.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_categoria_nombre)
                .text="Nombre" + categoria.nombre.toString()

            view.findViewById<TextView>(R.id.tv_ver_categoria_descripcion)
                .text="Descripcion" + categoria.descripcion.toString()

            view.findViewById<TextView>(R.id.tv_ver_categoria_estado)
                .text="Estado" + categoria.estado.toString()
        }

        return view
    }

}