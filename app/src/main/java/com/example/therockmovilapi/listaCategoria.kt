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
import com.example.therockmovilapi.Apis.CategoriaApiService
import kotlinx.coroutines.launch

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

        lifecycleScope.launch{
            try {
                val categorias = CategoriaApiService
                    .getApiManager()
                    .getCategoria()
                rv_lista_categorias.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_categorias.adapter = lista_categoria_adapter(categorias)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Categoría")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }
}