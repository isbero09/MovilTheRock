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
import com.example.therockmovilapi.Apis.UsuarioApiService
import kotlinx.coroutines.launch

class listaUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_usuario, container, false)

        val rv_lista_usuario = view.findViewById<RecyclerView>(R.id.rv_lista_usuario)

        view.findViewById<Button>(R.id.btn_nuevo_usuario).setOnClickListener {
            findNavController().navigate(R.id.action_listaUsuario_to_crearUsuario);
        }

        lifecycleScope.launch {
            try {
                val usuarios = UsuarioApiService
                    .getApiManager()
                    .getUsuario()
                rv_lista_usuario.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_usuario.adapter = lista_usuario_adapter(usuarios)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Usuario")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        return view
    }

}