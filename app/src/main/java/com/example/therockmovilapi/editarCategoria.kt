package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.CategoriaApiService
import com.example.therockmovilapi.Entities.Categoria
import kotlinx.coroutines.launch


class editarCategoria : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_editar_categoria, container, false)
        lifecycleScope.launch {
            var categoria= CategoriaApiService
                .getApiManager()
                .getCategoria(arguments?.getInt("id")?:0)
            view.findViewById<TextView>(R.id.categoria_nombre_editado)
                .text=categoria.nombre.toString()
            view.findViewById<TextView>(R.id.categoria_descripcion_editado)
                .text= categoria.descripcion.toString()
        }
        view.findViewById<Button>(R.id.btn_add_categoria).setOnClickListener {
                val nombre = view
                    .findViewById<EditText>(R.id.categoria_nombre_editado)
                    .text
                    .toString()
                    .trim()
                val descripcion = view.findViewById<EditText>(R.id.categoria_descripcion_editado).text.toString().trim()
                lifecycleScope.launch {
                    try {
                        CategoriaApiService
                            .getApiManager()
                            .putCategoria(
                                Categoria(
                                    (arguments?.getInt("id")?:0).toString(),
                                    nombre,
                                    descripcion),
                                arguments?.
                                getInt("id")?:0
                            )
                        Toast.makeText(
                            context,
                            "Categoría actualizada exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_editarCategoria_to_verCategoria, Bundle() .apply {
                                    putInt("id",arguments?.getInt("id")?:0 )
                                }
                            )
                    } catch (e: Exception) {
                        Toast.makeText(context,"Error al actualizar categoría:${e.localizedMessage}",
                        Toast.LENGTH_LONG).show()
                    }
                }
            }
        return view
    }

}