package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.CategoriaApiService
import com.example.therockmovilapi.Entities.Categoria
import kotlinx.coroutines.launch

class crearCategoria : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_categoria, container, false)

        view.findViewById<Button>(R.id.btn_add_categoria).setOnClickListener {

            val nombre = view.findViewById<EditText>(R.id.categoria_nombre_nuevo)
                .text.toString().trim()

            val descripcion = view.findViewById<EditText>(R.id.categoria_descripcion_nuevo)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.categoria_estado_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    CategoriaApiService.getApiManager().postCategoria(
                        Categoria( nombre,  descripcion, estado )
                    )
                    Toast.makeText(
                        context, "Categoría registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar categoría: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        return view
    }
}